package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:24 오후
*/
@Repository
public interface PlaceRepository extends JpaRepository<Place,Integer> {
}
