package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.RegionColor;
import com.example.koreaguide.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/21 7:15 오전
*/
@Repository
public interface RegionColorRepository extends JpaRepository<RegionColor,Integer> {
    RegionColor findByAreacodeAndUser(Integer areacode, User user);

}
