package com.example.koreaguide.service;

import com.example.koreaguide.ifs.CrudInterface;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.exception.EmailNotExistedException;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.exception.PasswordWrongException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.SessionRequestDto;
import com.example.koreaguide.model.network.request.UserApiRequest;
import com.example.koreaguide.model.network.response.SessionResponseDto;
import com.example.koreaguide.model.network.response.UserApiResponse;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.swing.text.html.Option;
import javax.validation.constraints.Email;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public UserApiResponse create(Header<UserApiRequest> request) {
        // 회원가입!

        // 1.request data 가져오고
        UserApiRequest body = request.getData();

        // 비번 암호화
        String encodedPassword = passwordEncoder.encode(body.getPassword());

        // 이메일 중복 체크 한번더 확인
        Optional<User> userWithEmail = userRepository.findByEmail(body.getEmail());
        if(userWithEmail.isPresent()){
            throw new KoreaGuideException(KoreaGuideError.DUPLICATE_ERROR,"Email already exists");
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
//            return Header.OK(response(newUser),HttpStatus.CREATED);
            return response(newUser);
        }
    }

    public UserApiResponse read(Integer id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(selectedUser->response(selectedUser))
                .orElseThrow(()->
                        new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND,"user")
                );

    }

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
            if(!body.getLevel().toString().isEmpty()){
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

    public Header delete(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user
                .map(userSelected->{
                            userRepository.delete(userSelected);
                            return Header.OK();
                        }
                )
                .orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND,"user"));
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

    private UserApiResponse response(User user,String token){
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
        return userApiResponse;
    }
    // 이메일 중복 검사
    public Header<UserApiResponse> checkDuplicateEmail(Header<UserApiRequest> request) {
        Optional<User> user = userRepository.findByEmail(request.getData().getEmail());
        if(user.isPresent()){
            throw new KoreaGuideException(KoreaGuideError.DUPLICATE_ERROR,"Email already exists");
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

    public UserApiResponse login(Header<UserApiRequest> request) {
        User user = userRepository.findByEmail(request.getData().getEmail())
                .orElseThrow(()-> new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND,"user"));

        if (!passwordEncoder.matches(request.getData().getPassword(), user.getPassword())) {
            throw new KoreaGuideException(KoreaGuideError.WRONG_PASSWORD,"user");
        }

        String token = jwtUtil.createAccessToken(user.getId(),user.getNickname());

        if(LocalDate.now().getDayOfWeek().compareTo(DayOfWeek.MONDAY)==0){
            System.out.println("NOW: "+LocalDate.now().getDayOfWeek());
            System.out.println("Mond: "+DayOfWeek.MONDAY);
            user.setWeekAttendance(1);
            user.setLastLoginAt(LocalDate.now());
            userRepository.save(user);
        }
        if(user.getLastLoginAt().compareTo(LocalDate.now())!=0){
            System.out.println("LAST LOGIN: "+user.getLastLoginAt().toString());
            System.out.println("NOW!!: "+LocalDate.now().toString());
            Integer userWeekAttendance = user.getWeekAttendance();
            user.setWeekAttendance(userWeekAttendance+1);
            user.setLastLoginAt(LocalDate.now());
            userRepository.save(user);
        }
        return response(user,token);
    }

}
