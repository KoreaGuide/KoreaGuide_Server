package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MapFile;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.repository.MapFileRepository;
import com.example.koreaguide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

/*
 * @author : Jisoo Kim
 * @date: 2021/05/26 8:39 오후
*/
@Service
public class MapFileApiLogicService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MapFileRepository mapFileRepository;

    public Header storeFile(MultipartFile file, Integer userId) {
        String fileName = file.getName();

        Optional<User> user = userRepository.findById(userId);
        Header ret = user.map(selectedUser->{
            MapFile mapFile = mapFileRepository.getByUser(selectedUser);
            mapFile.setFileName(fileName)
                    .setUser(selectedUser)
                    .setCreatedAt(LocalDateTime.now());
            mapFileRepository.save(mapFile);
            return Header.OK("File upload successful");
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER));
        return ret;
    }
}
