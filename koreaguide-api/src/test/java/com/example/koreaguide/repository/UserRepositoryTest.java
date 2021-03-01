package com.example.koreaguide.repository;

import com.example.koreaguide.KoreaguideApplicationTests;
import com.example.koreaguide.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDateTime;
import java.util.Optional;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/01 6:07 오후
*/

public class UserRepositoryTest extends KoreaguideApplicationTests {
    @Autowired          // DI ( Dependency Injection) -- 직접 객체 만들지 않고, 스프링이 이 객체들 관리하게!
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        user.setEmail("jisoo@naver.com");
        user.setPassword("2222");
        user.setLevel("MID");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("Admin");

        User newUser=userRepository.save(user);
        System.out.println("newUser: "+newUser);
        assertThat(newUser.getEmail()).isEqualTo("jisoo@naver.com");
        assertThat(newUser.getPassword()).isEqualTo("2222");
        assertThat(newUser.getLevel()).isEqualTo("MID");
    }

    @Test
    public void read(){
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser->{
            System.out.println("user: "+selectUser);
            System.out.println("email: "+selectUser.getEmail());
        });
    }

    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectedUser->{
            selectedUser.setEmail("jisoo@naver.com");
            selectedUser.setLevel("HIGH");
            selectedUser.setPassword("2222");

           User newUser= userRepository.save(selectedUser);
           assertThat(newUser.getEmail()).isEqualTo("jisoo@naver.com");
           assertThat(newUser.getLevel()).isEqualTo("HIGH");
           assertThat(newUser.getPassword()).isEqualTo("2222");
           assertThat(newUser.getId()).isEqualTo(1L);
        });


    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectedUser->{
            userRepository.delete(selectedUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);
        assertThat(deleteUser.isPresent()).isEqualTo(false);
    }
}
