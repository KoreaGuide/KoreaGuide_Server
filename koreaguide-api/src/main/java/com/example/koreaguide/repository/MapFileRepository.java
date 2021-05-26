package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.MapFile;
import com.example.koreaguide.model.entity.MyPlace;
import com.example.koreaguide.model.entity.Place;
import com.example.koreaguide.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * @author : Jisoo Kim
 * @date: 2021/05/26 8:38 오후
*/
@Repository
public interface MapFileRepository extends JpaRepository<MapFile,Integer> {
    MapFile getByUser(User user);

}
