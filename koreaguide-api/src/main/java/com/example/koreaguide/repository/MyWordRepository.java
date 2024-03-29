package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.MyWordFolder;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyWordRepository extends JpaRepository<MyWord,Integer> {

    List<MyWord> findAllByMyWordFolderId(Integer myWordFolderId);

    Optional<MyWord> findByMyWordFolderAndWord(MyWordFolder myWordFolder, Word word);

}
