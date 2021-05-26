package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MapFile;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.repository.MapFileRepository;
import com.example.koreaguide.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        final String uploadPath = Paths.get("koreaguide-api","src","main","java","com","example","koreaguide","mapFiles").toString();
        File dir = new File(uploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        Optional<User> user = userRepository.findById(userId);
        Header ret = user.map(selectedUser->{
            final String saveName = "mapFile_"+selectedUser.getId() + "." + extension;

            File target = new File(uploadPath, saveName);
            try {
                file.transferTo(target);
            } catch (IOException e) {
                e.printStackTrace();
            }

//            MapFile mapFile = mapFileRepository.getByUser(selectedUser);
//            mapFile.setFileName(fileName)
//                    .setUser(selectedUser)
//                    .setCreatedAt(LocalDateTime.now());
//            mapFileRepository.save(mapFile);
            MapFile mapFile = MapFile.builder()
                    .fileName(saveName)
                    .createdAt(LocalDateTime.now())
                    .user(selectedUser)
                    .originalName(file.getOriginalFilename())
                    .size(file.getSize())
                    .build();
            mapFileRepository.save(mapFile);
            return Header.OK("File upload successful");
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER));
        return ret;
    }

//    public Resource loadFileAsResource(String fileName) {
//        try {
//            Path filePath = this.fileLocation.resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//
//            if(resource.()) {
//                return resource;
//            }else {
//                throw new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYPLACE);
//            }
//        }catch(MalformedURLException e) {
//            throw new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYPLACE);
//        }
//    }

}
