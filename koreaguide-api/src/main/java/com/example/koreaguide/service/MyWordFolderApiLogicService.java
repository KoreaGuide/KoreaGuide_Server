package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MyWordFolder;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.enumclass.UserStatus;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.MyWordFolderApiRequest;
import com.example.koreaguide.model.network.response.MyWordFolderApiResponse;
import com.example.koreaguide.model.network.response.MyWordListApiResponse;
import com.example.koreaguide.repository.MyWordFolderRepository;
import com.example.koreaguide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/21 7:49 오전
*/
@Service
public class MyWordFolderApiLogicService {
    @Autowired
    MyWordFolderRepository myWordFolderRepository;

    @Autowired
    UserRepository userRepository;

    public MyWordFolderApiResponse create(Header<MyWordFolderApiResponse> request) {
        MyWordFolderApiResponse body = request.getData();
        Optional<MyWordFolder> myWordFolder = myWordFolderRepository.findByFolderName(body.getFolderName());
        if(myWordFolder.isPresent()){
            throw new KoreaGuideException(KoreaGuideError.DUPLICATE_ERROR_MYWORDFOLDER);
        }else {
            // 2. user 생성
            User user = userRepository.getOne(body.getUserId());
            if(user==null){
                throw new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER);
            }else {
                MyWordFolder newMyWordFolder = MyWordFolder.builder()
                        .folderName(body.getFolderName())
                        .wordCount(0)
                        .user(user).build();
                MyWordFolder newCreatedFolder = myWordFolderRepository.save(newMyWordFolder);
                response(newCreatedFolder);
                return response(newMyWordFolder);
            }
        }
    }

    private MyWordFolderApiResponse response(MyWordFolder myWordFolder){
        MyWordFolderApiResponse myWordFolderApiResponse = MyWordFolderApiResponse.builder()
                .userId(myWordFolder.getUser().getId())
                .wordFolderId(myWordFolder.getId())
                .folderName(myWordFolder.getFolderName())
                .build();
        return myWordFolderApiResponse;
    }

    public List<MyWordFolderApiResponse> getAllFolderList(Integer id) {
        List<MyWordFolder> myWordFolderList = myWordFolderRepository.findAllByUserId(id);
        if(myWordFolderList.isEmpty()){
            throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_MYWORDFOLDER);
        }else{
            return responseAsList(myWordFolderList);
        }
    }

    private List<MyWordFolderApiResponse> responseAsList(List<MyWordFolder> myWordFolder){
        List<MyWordFolderApiResponse> myWordFolderApiResponseList =new ArrayList<MyWordFolderApiResponse>();
        for(int i=0;i<myWordFolder.size();i++){
            MyWordFolderApiResponse myWordFolderApiResponse = MyWordFolderApiResponse.builder()
                    .wordFolderId(myWordFolder.get(i).getId())
                    .folderName(myWordFolder.get(i).getFolderName())
                    .wordCount(myWordFolder.get(i).getWordCount())
                    .build();
            myWordFolderApiResponseList.add(myWordFolderApiResponse);
        }
        return myWordFolderApiResponseList;
    }

    public MyWordFolderApiResponse deleteOneFolder(Integer id, Header<MyWordFolderApiRequest> request) {
        MyWordFolderApiRequest body = request.getData();
        Optional<MyWordFolder> myWordFolder = myWordFolderRepository.findById(body.getWordFolderId());
        return myWordFolder.map(selectedFolder->{
            myWordFolderRepository.delete(selectedFolder);
            MyWordFolderApiResponse myWordFolderApiResponse = MyWordFolderApiResponse.builder().build();
            return myWordFolderApiResponse;
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORDFOLDER));
    }

    public MyWordFolderApiResponse updateFolderName(Integer id, Header<MyWordFolderApiRequest> request) {
        MyWordFolderApiRequest body = request.getData();
        Optional<MyWordFolder> myWordFolder = myWordFolderRepository.findById(body.getWordFolderId());
        return myWordFolder.map(selectedFolder->{
            selectedFolder.setFolderName(body.getFolderName());
            MyWordFolder newFolder =myWordFolderRepository.save(selectedFolder);
            return response(newFolder);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORDFOLDER));
    }
}
