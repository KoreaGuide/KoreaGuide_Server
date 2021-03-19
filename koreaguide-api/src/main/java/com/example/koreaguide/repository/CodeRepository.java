package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/19 9:30 오후
*/

@Repository
public interface CodeRepository extends JpaRepository<Code,Integer> {
}
