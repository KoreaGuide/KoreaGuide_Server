package com.example.koreaguide.service;

import com.example.koreaguide.ifs.CrudInterface;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.exception.EmailNotExistedException;
import com.example.koreaguide.model.exception.PasswordWrongException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.SessionRequestDto;
import com.example.koreaguide.model.network.request.UserApiRequest;
import com.example.koreaguide.model.network.response.SessionResponseDto;
import com.example.koreaguide.model.network.response.UserApiResponse;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.validation.constraints.Email;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // 회원가입!

        // 1.request data 가져오고
        UserApiRequest body = request.getData();

        // 비번 암호화
        String encodedPassword = passwordEncoder.encode(body.getPassword());

        // 이메일 중복 체크 한번더 확인
        Optional<User> userWithEmail = userRepository.findByEmail(body.getEmail());
        if(userWithEmail.isPresent()){
            return Header.CONFLICTERROR("Email already exists");
        }else {
            // 2. user 생성
            User user = User.builder()
                    .email(body.getEmail())
                    .password(encodedPassword)
                    .level(body.getLevel())
                    .nickname(body.getNickname())
                    .createdAt(LocalDateTime.now())
                    .createdBy("Admin")
                    .weekAttendance(0)
                    .build();
            String token = jwtUtil.createAccessToken(body.getId(),body.getNickname());
            System.out.println("ACCESS TOKEN"+token);
            User newUser = userRepository.save(user);

            // 3. 생성된 데이터 --> userapiresponse 리턴
            return Header.OK(response(newUser),HttpStatus.CREATED);
        }
    }

    @Override
    public Header<UserApiResponse> read(Integer id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(selectedUser-> Header.OK(response(selectedUser),HttpStatus.FOUND))
                .orElseGet(
                        ()->Header.NOTFOUNDERROR("Cannot find user")
                );

    }

    @Override
    public Header<UserApiResponse> update(Integer id,Header<UserApiRequest> request) {
        UserApiRequest body = request.getData();
        Optional<User> user = userRepository.findById(id);
        if(user == null){
            Header.NOTFOUNDERROR("Cannot find user");
        }
        String encodedPassword = passwordEncoder.encode(body.getPassword());
        return user.map(selectedUser->{
            if(!body.getEmail().isEmpty()){
                selectedUser.setEmail(body.getEmail());
            }
            if(!body.getNickname().isEmpty()){
                selectedUser.setNickname(body.getNickname());
            }
            if(!body.getLevel().isEmpty()){
                selectedUser.setLevel(body.getLevel());
            }
            if(!body.getPassword().isEmpty()){
                selectedUser.setPassword(encodedPassword);
            }
            selectedUser.setCreatedAt(user.get().getCreatedAt())
                        .setCreatedBy(user.get().getCreatedBy());
            return selectedUser;
            })
                .map(updatedUser -> userRepository.save(updatedUser))
                .map(finalUser->Header.OK(response(finalUser),HttpStatus.OK))
                .orElseGet(()->Header.NOTFOUNDERROR("Cannot find user"));
    }

    @Override
    public Header delete(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user
                .map(userSelected->{
                            userRepository.delete(userSelected);
                            return Header.OK();
                        }
                )
                .orElseGet(()->Header.ERROR("Cannot find user"));
    }

    private UserApiResponse response(User user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .level(user.getLevel())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .weekAttendance(user.getWeekAttendance())
                .lastLoginAt(user.getLastLoginAt())
                .build();
        return userApiResponse;
    }

    private Header<UserApiResponse> response(User user,String token){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .level(user.getLevel())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .lastLoginAt(user.getLastLoginAt())
                .weekAttendance(user.getWeekAttendance())
                .token(token)
                .build();
        return Header.OK(userApiResponse,HttpStatus.OK);
    }
    // 이메일 중복 검사
    public Header<UserApiResponse> checkDuplicateEmail(Header<UserApiRequest> request) {
        Optional<User> user = userRepository.findByEmail(request.getData().getEmail());
        if(user.isPresent()){
            return Header.CONFLICTERROR("Email already exists");
        }else{
            return Header.OK("Email good to use");
        }
    }

    public Header<SessionResponseDto> authenticate(Header<SessionRequestDto> request){
        SessionRequestDto body = request.getData();
        String email = body.getEmail();
        String password = body.getPassword();
        // 해당 이메일을 가진 사용자가 존재하는지 여부
        User user=userRepository.findByEmail(email).orElseThrow(()->new EmailNotExistedException(email));

        // 해당 이메일 & 비번을 가진 사용자가 존재하는지 여부
        if(!passwordEncoder.matches(password,user.getPassword())){
//            throw new PasswordWrongException();
        }
        return null;
    }

    public Header<UserApiResponse> login(Header<UserApiRequest> request) {
        User user = userRepository.findByEmail(request.getData().getEmail())
                .orElseThrow(()-> new EmailNotExistedException("cannot find user"));

        if (!passwordEncoder.matches(request.getData().getPassword(), user.getPassword())) {
            return Header.CONFLICTERROR("Wrong Password");
        }
        String token = jwtUtil.createAccessToken(user.getId(),user.getNickname());
        if(LocalDate.now().getDayOfWeek()== DayOfWeek.MONDAY){
            user.setWeekAttendance(1);
            user.setLastLoginAt(LocalDate.now());
        }
        if(user.getLastLoginAt()!=LocalDate.now()){
            Integer userWeekAttendance = user.getWeekAttendance();
            user.setWeekAttendance(userWeekAttendance+1);
            user.setLastLoginAt(LocalDate.now());
        }
        return response(user,token);
    }
}
