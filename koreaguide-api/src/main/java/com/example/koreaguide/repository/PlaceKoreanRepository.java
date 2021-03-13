package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.PlaceKorean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:25 오후
*/

@Repository
public interface PlaceKoreanRepository extends JpaRepository<PlaceKorean,Integer> {
}
