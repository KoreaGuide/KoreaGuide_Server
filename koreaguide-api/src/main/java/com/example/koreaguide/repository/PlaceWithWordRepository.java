package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.PlaceWithWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceWithWordRepository extends JpaRepository<PlaceWithWord,Integer> {
}
