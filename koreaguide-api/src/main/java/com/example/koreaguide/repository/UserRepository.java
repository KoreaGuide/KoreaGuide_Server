package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/01 6:04 오후
*/

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
