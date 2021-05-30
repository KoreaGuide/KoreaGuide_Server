package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MapFile;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.repository.MapFileRepository;
import com.example.koreaguide.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
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
//        final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        final String uploadPath = Paths.get("koreaguide-api","src","main","java","com","example","koreaguide","mapFiles").toString();
        File dir = new File(uploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        Optional<User> user = userRepository.findById(userId);
        Header ret = user.map(selectedUser->{
            final String saveName = "mapFile_"+selectedUser.getId() + "." + "geojson";

            File target = new File(uploadPath, saveName);
            try {
                file.transferTo(target);
            } catch (IOException e) {
                e.printStackTrace();
            }

            MapFile mapFile = mapFileRepository.getByUser(selectedUser);
            mapFile.setFileName(saveName)
                    .setUser(selectedUser)
                    .setOriginalName(file.getOriginalFilename())
                    .setSize(file.getSize())
                    .setCreatedAt(LocalDateTime.now());
            mapFileRepository.save(mapFile);

//            MapFile mapFile = MapFile.builder()
//                    .fileName(saveName)
//                    .createdAt(LocalDateTime.now())
//                    .user(selectedUser)
//                    .originalName(file.getOriginalFilename())
//                    .size(file.getSize())
//                    .build();
//            mapFileRepository.save(mapFile);
            return Header.OK("File upload successful");
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER));
        return ret;
    }

    public ResponseEntity downloadFile(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(selectedUser->{
            MapFile mapFile = mapFileRepository.getByUser(selectedUser);
            Path path = Paths.get("koreaguide-api","src","main","java","com","example","koreaguide","mapFiles",mapFile.getFileName());
            File file = new File(path.toString());
//            try {
//                String contentType = Files.probeContentType(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            ByteArrayResource resource = null;
            try {
                resource = new ByteArrayResource(Files.readAllBytes(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);

//            InputStream in = getClass().getResourceAsStream("koreaguide-api/src/main/java/com/example/koreaguide/mapFiles/"+"mapFile_"+selectedUser.getId() + "." + "json");
//            return IOUtils.toByteArray(in);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER));
    }

//    public Resource loadFileAsResource(String fileName) {
//        try {
//            Path path = Paths.get("koreaguide-api","src","main","java","com","example","koreaguide","mapFiles",mapFile.getFileName());
//            Resource resource = (Resource) new UrlResource(path.toUri());
//            if (resource.exists()) {
//                return resource;
//            } else {
////                throw new FileNotFoundException("File not found " + fileName);
//            }
//        } catch (MalformedURLException ex) {
////            throw new FileNotFoundException("File not found " + fileName, ex);
//        }
//    }

}
