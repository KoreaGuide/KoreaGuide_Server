package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.MyTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/*
 * @author : Jisoo Kim
 * @date: 2021/04/11 12:12 오전
*/
@Repository
public interface MyTestResultRepository extends JpaRepository<MyTestResult,Integer> {
    Optional<MyTestResult> findByDateAndUserId(LocalDate date, Integer userId);
}
