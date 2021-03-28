package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.MyPlace;
import com.example.koreaguide.model.entity.Place;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.enumclass.PlaceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:23 오후
*/
@Repository
public interface MyPlaceRepository extends JpaRepository<MyPlace,Integer> {
    MyPlace findByPlace(Place place);
    Optional<MyPlace> findByPlaceAndUser(Place place, User user);
    List<MyPlace> findAllByUserIdAndStatus(Integer userId, PlaceStatus status);
    List<MyPlace> findAllByUserId(Integer userId);


}
