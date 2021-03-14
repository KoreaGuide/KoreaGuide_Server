package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.TodaysPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:26 오후
*/
@Repository
public interface TodaysPlaceRepository extends JpaRepository<TodaysPlace,Integer> {
    TodaysPlace findByCreatedAt(LocalDate createdAt);
}
