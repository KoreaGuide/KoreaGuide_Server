package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.MyWordFolder;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.enumclass.UserStatus;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.Pagination;
import com.example.koreaguide.model.network.request.MyWordFolderApiRequest;
import com.example.koreaguide.model.network.response.LearnWordApiResponse;
import com.example.koreaguide.model.network.response.MyWordFolderApiResponse;
import com.example.koreaguide.model.network.response.MyWordListApiResponse;
import com.example.koreaguide.model.network.response.PlaceDetailWordApiResponse;
import com.example.koreaguide.repository.MyWordFolderRepository;
import com.example.koreaguide.repository.MyWordRepository;
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

    @Autowired
    MyWordRepository myWordRepository;

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
            List<MyWord> myWordList =myWordRepository.findAllByMyWordFolderId(body.getWordFolderId());
            if(!myWordList.isEmpty()){
                for(int i=0;i<myWordList.size();i++){
                    myWordRepository.delete(myWordList.get(i));
                }
            }
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

    public LearnWordApiResponse getWordforLearning(Integer id, Header<MyWordFolderApiRequest> request, Integer pageNumber) {
        Integer pageSize=1;
        MyWordFolderApiRequest body = request.getData();

        Optional<MyWordFolder> myWordFolder = myWordFolderRepository.findById(body.getWordFolderId());
        return myWordFolder.map(selectedWordFolder->{
            List<MyWord> myWord = myWordRepository.findAllByMyWordFolderId(selectedWordFolder.getId());
            if(myWord.isEmpty()){
                throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_MYWORDFOLDER);
            }
            Integer totalPage;
            if(myWord.size()%pageSize!=0){
                totalPage=myWord.size()/pageSize+1;
            }else{
                totalPage=myWord.size()/pageSize;
            }
            return responseForLearnWord(totalPage,pageNumber,myWord,id,selectedWordFolder);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORDFOLDER));

    }

    private LearnWordApiResponse responseForLearnWord(Integer totalPage, Integer currentPage, List<MyWord> myWord, Integer id, MyWordFolder selectedWordFolder) {
        if(currentPage>totalPage || currentPage<1){
            throw new KoreaGuideException(KoreaGuideError.PAGINATION_OUT_OF_INDEX);
        }
        Integer startItemindex,endItemindex;
        if(currentPage == 1){
            startItemindex=0;
        }else{
            // page 마다 1개씩으로 바껴서 currentPage-1
            startItemindex = currentPage-1;
        }
        endItemindex = startItemindex+1;
        if(endItemindex>=myWord.size()){
            endItemindex = startItemindex+1;
        }

        Pagination pagination = Pagination.builder()
                .currentPage(currentPage)
                .currentElements(endItemindex-startItemindex)
                .totalPages(totalPage)
                .totalElements(myWord.size()).build();
        List<MyWordListApiResponse> myWordListApiResponseList =new ArrayList<MyWordListApiResponse>();
        for(int i=startItemindex;i<endItemindex;i++){
            MyWordListApiResponse myWordListApiResponse = MyWordListApiResponse.builder()
                    .id(myWord.get(i).getId())
                    .myWordStatus(myWord.get(i).getWordStatus())
                    .wordEng(myWord.get(i).getWord().getWordEng())
                    .wordKor(myWord.get(i).getWord().getWordKor())
                    .meaningEng1(myWord.get(i).getWord().getMeaningEng1())
                    .meaningEng2(myWord.get(i).getWord().getMeaningEng2())
                    .meaningKor1(myWord.get(i).getWord().getMeaningKor1())
                    .meaningKor1(myWord.get(i).getWord().getMeaningKor2())
                    .image(myWord.get(i).getWord().getImage())
                    .audio(myWord.get(i).getWord().getAudio())
                    .pronunciationEng(myWord.get(i).getWord().getPronunciationEng())
                    .build();
            myWordListApiResponseList.add(myWordListApiResponse);
        }
        LearnWordApiResponse learnWordApiResponse = LearnWordApiResponse.builder()
                .folderId(selectedWordFolder.getId())
                .folderName(selectedWordFolder.getFolderName())
                .pagination(pagination)
                .wordList(myWordListApiResponseList).build();
        return learnWordApiResponse;
    }

    public LearnWordApiResponse getWordListforLearning(Integer id, Integer folderId) {

        Optional<MyWordFolder> myWordFolder = myWordFolderRepository.findById(folderId);
        return myWordFolder.map(selectedWordFolder->{
            List<MyWord> myWord = myWordRepository.findAllByMyWordFolderId(selectedWordFolder.getId());
            if(myWord.isEmpty()){
                throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_MYWORDFOLDER);
            }
            Integer totalPage;
            return responseForLearnWordList(myWord,id,selectedWordFolder);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORDFOLDER));
    }

    private LearnWordApiResponse responseForLearnWordList(List<MyWord> myWord, Integer id, MyWordFolder selectedWordFolder) {
        List<MyWordListApiResponse> myWordListApiResponseList =new ArrayList<MyWordListApiResponse>();
        for(int i=0;i<myWord.size();i++){
            MyWordListApiResponse myWordListApiResponse = MyWordListApiResponse.builder()
                    .id(myWord.get(i).getId())
                    .myWordStatus(myWord.get(i).getWordStatus())
                    .wordEng(myWord.get(i).getWord().getWordEng())
                    .wordKor(myWord.get(i).getWord().getWordKor())
                    .meaningEng1(myWord.get(i).getWord().getMeaningEng1())
                    .meaningEng2(myWord.get(i).getWord().getMeaningEng2())
                    .meaningKor1(myWord.get(i).getWord().getMeaningKor1())
                    .meaningKor2(myWord.get(i).getWord().getMeaningKor2())
                    .image(myWord.get(i).getWord().getImage())
                    .audio(myWord.get(i).getWord().getAudio())
                    .pronunciationEng(myWord.get(i).getWord().getPronunciationEng())
                    .build();
            myWordListApiResponseList.add(myWordListApiResponse);
        }
        LearnWordApiResponse learnWordApiResponse = LearnWordApiResponse.builder()
                .folderId(selectedWordFolder.getId())
                .folderName(selectedWordFolder.getFolderName())
                .wordList(myWordListApiResponseList).build();
        return learnWordApiResponse;
    }

}
