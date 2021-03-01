package com.example.koreaguide.service;

import com.example.koreaguide.ifs.CrudInterface;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.UserApiRequest;
import com.example.koreaguide.model.network.response.UserApiResponse;
import com.example.koreaguide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // 1.request data 가져오고
        UserApiRequest body = request.getData();

        // 2. user 생성
        User user = User.builder()
                .email(body.getEmail())
                .password(body.getPassword())
                .level(body.getLevel())
                .nickname(body.getNickname())
                .createdAt(LocalDateTime.now())
                .createdBy("Admin")
                .build();
        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 --> userapiresponse 리턴
        return response(newUser);
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
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest body = request.getData();
        Optional<User> user = userRepository.findById(body.getId());
        return user.map(selectedUser->{
            selectedUser.setEmail(body.getEmail())
                .setPassword(body.getPassword())
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
}
