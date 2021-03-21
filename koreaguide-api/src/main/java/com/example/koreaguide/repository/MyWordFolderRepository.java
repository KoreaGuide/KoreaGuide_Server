package com.example.koreaguide.repository;

import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.MyWordFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/21 7:34 오전
*/
@Repository
public interface MyWordFolderRepository extends JpaRepository<MyWordFolder,Integer> {
    Optional<MyWordFolder> findByFolderName(String folderName);

    List<MyWordFolder> findAllByUserId(Integer userId);

//    List<MyWordFolder> findAllById(Integer id);

}
