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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
            return Header.ERROR("Email already exists");
        }else {
            // 2. user 생성
            User user = User.builder()
                    .email(body.getEmail())
                    .password(encodedPassword)
                    .level(body.getLevel())
                    .nickname(body.getNickname())
                    .createdAt(LocalDateTime.now())
                    .createdBy("Admin")
                    .build();
            String token = jwtUtil.createAccessToken(body.getId(),body.getNickname());
            System.out.println("ACCESS TOKEN"+token);
            User newUser = userRepository.save(user);

            // 3. 생성된 데이터 --> userapiresponse 리턴
            return response(newUser);
        }
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(selectedUser-> response(selectedUser))
                .orElseGet(
                        ()->Header.ERROR("Cannot find user")
                );

    }

    @Override
    public Header<UserApiResponse> update(Long id,Header<UserApiRequest> request) {
        UserApiRequest body = request.getData();
        Optional<User> user = userRepository.findById(id);
        String encodedPassword = passwordEncoder.encode(body.getPassword());
        return user.map(selectedUser->{
            selectedUser.setEmail(body.getEmail())
                .setPassword(encodedPassword)
                .setNickname(body.getNickname())
                .setLevel(body.getLevel())
                .setCreatedAt(user.get().getCreatedAt())
                .setCreatedBy(user.get().getCreatedBy());
                return selectedUser;
            })
                .map(updatedUser -> userRepository.save(updatedUser))
                .map(finalUser->response(finalUser))
                .orElseGet(()->Header.ERROR("Cannot find user"));
    }

    @Override
    public Header delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user
                .map(userSelected->{
                            userRepository.delete(userSelected);
                            return Header.OK();
                        }
                )
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .level(user.getLevel())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .build();
        return Header.OK(userApiResponse);
    }

    // 이메일 중복 검사
    public Header<UserApiResponse> checkDuplicateEmail(Header<UserApiRequest> request) {
        Optional<User> user = userRepository.findByEmail(request.getData().getEmail());
        if(user.isPresent()){
            return Header.OK("Email already exists");
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
}
