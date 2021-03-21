package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.Home;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.enumclass.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:22 오후
*/
@Repository
public interface HomeRepository extends JpaRepository<Home,Integer> {
   Home findByCreatedAt(LocalDate createdAt);
}
