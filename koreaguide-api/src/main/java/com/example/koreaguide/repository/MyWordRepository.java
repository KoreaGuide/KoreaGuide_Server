package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.MyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyWordRepository extends JpaRepository<MyWord,Integer> {
}
