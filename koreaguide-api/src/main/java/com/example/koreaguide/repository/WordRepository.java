package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:27 오후
*/
@Repository
public interface WordRepository extends JpaRepository<Word,Integer> {
}
