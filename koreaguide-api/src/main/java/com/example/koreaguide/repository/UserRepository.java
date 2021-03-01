package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/01 6:04 오후
*/

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
