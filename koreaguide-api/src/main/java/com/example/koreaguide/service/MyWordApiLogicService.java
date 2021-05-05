package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.MyWordFolder;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.entity.Word;
import com.example.koreaguide.model.enumclass.MyWordStatus;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.MyWordApiRequest;
import com.example.koreaguide.model.network.response.MyWordApiResponse;
import com.example.koreaguide.model.network.response.MyWordListApiResponse;
import com.example.koreaguide.repository.MyWordFolderRepository;
import com.example.koreaguide.repository.MyWordRepository;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/15 8:29 오후
*/
@Service
public class MyWordApiLogicService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private MyWordRepository myWordRepository;

    @Autowired
    private MyWordFolderRepository myWordFolderRepository;

    public MyWordApiResponse addWordToMyWordList(Integer id, Header<MyWordApiRequest> request) {
        //request에 있는 id 갖는 word 찾아서
        MyWordApiRequest body = request.getData();
        Optional<Word> selectedWord = wordRepository.findById(body.getWordId());
        return selectedWord.map(word->{
//            Optional<User> user = userRepository.findById(id);
            // 폴더 아이디로 폴더 찾고
            Optional<MyWordFolder> wordFolder = myWordFolderRepository.findById(body.getWordFolderId());
            MyWord newMyWord=wordFolder.map(selectedWordFolder->{
                // 그 폴더가 있을때
                if(myWordRepository.findByMyWordFolderAndWord(selectedWordFolder,word).isPresent()){
                    // 이미 해당 단어를 mywordlist에 해당 유저가 폴더에 갖고있을때
                    System.out.println("ALREADY HAVE!!!");
                    System.out.println("________________________");
                    System.out.println("selected wordFolder: "+selectedWordFolder+"   word:"+word);
                    throw new KoreaGuideException(KoreaGuideError.DUPLICATE_ERROR_MYWORD,"Already exists");
                }
                selectedWordFolder.setWordCount(selectedWordFolder.getWordCount()+1);
                myWordFolderRepository.save(selectedWordFolder);
                MyWord myWord = MyWord.builder()
                        .word(word)
                        .myWordFolder(selectedWordFolder)
                        .wordStatus(MyWordStatus.IN_MY_LIST).build();
                return myWordRepository.save(myWord);

                })
                    .orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORDFOLDER));
            System.out.println("MY NEW WORD: "+newMyWord);
            return responseForAdd(newMyWord);})
                .orElseThrow(()->
                        new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_WORD,"word")
                );

    }
    private MyWordApiResponse responseForAdd(MyWord myWord){
        //해당 폴더에 있는 워드에 대한 count 보여줌
        List<MyWord> myWordList = myWordRepository.findAllByMyWordFolderId(myWord.getMyWordFolder().getId());
        System.out.println("WORD LIST!!! "+myWordList);
        MyWordApiResponse myWordApiResponse = MyWordApiResponse.builder()
                .myWordFolderId(myWord.getMyWordFolder().getId())
                .previousWordCount(myWordList.size()-1)
                .nowWordCount(myWordList.size())
                .build();
        return myWordApiResponse;
    }

    private MyWordApiResponse response(MyWord myword){
        //해당 폴더에 있는 워드들 모두 보여주기
        List<MyWord> myWordList = myWordRepository.findAllByMyWordFolderId(myword.getMyWordFolder().getId());
        System.out.println("MY WORD LIST = "+myWordList);
        List<MyWordListApiResponse> myWordListApiResponseList =new ArrayList<MyWordListApiResponse>();
        for(int i=0;i<myWordList.size();i++){
            MyWordListApiResponse myWordListApiResponse =MyWordListApiResponse.builder()
                    .id(myWordList.get(i).getWord().getId())
                    .wordEng(myWordList.get(i).getWord().getWordEng())
                    .wordKor(myWordList.get(i).getWord().getWordKor())
                    .meaningEng1(myWordList.get(i).getWord().getMeaningEng1())
                    .meaningEng2(myWordList.get(i).getWord().getMeaningEng2())
                    .meaningKor1(myWordList.get(i).getWord().getMeaningKor1())
                    .meaningKor1(myWordList.get(i).getWord().getMeaningKor2())
                    .image(myWordList.get(i).getWord().getImage())
                    .audio(myWordList.get(i).getWord().getAudio())
                    .pronunciationEng(myWordList.get(i).getWord().getPronunciationEng())
                    .myWordStatus(myWordList.get(i).getWordStatus())
                    .build();
            myWordListApiResponseList.add(myWordListApiResponse);
        }
        System.out.println("WORD API RESPONSE LIST = "+myWordListApiResponseList);
        MyWordApiResponse myWordApiResponse = MyWordApiResponse.builder()
                .myWordFolderId(myword.getMyWordFolder().getId())
                .nowWordCount(myWordList.size())
                .myWordList(myWordListApiResponseList)
                .build();
        return myWordApiResponse;
    }


    public MyWordApiResponse getMyWordList(Integer id,Integer wordFolderIdt) {
        System.out.println("INSIDE");
        System.out.println("MY folder id: "+wordFolderIdt);
        List<MyWord> myWordList = myWordRepository.findAllByMyWordFolderId(wordFolderIdt);
        System.out.println("MY WORD LIST"+myWordList);
        if(myWordList.isEmpty()){
            throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_MYWORD,"myWordList");
        }
        System.out.println("MY WORD LIST"+myWordList);
        return response(myWordList.get(0));
    }

    public MyWordApiResponse deleteMyWord(Header<MyWordApiRequest> request) {
        MyWordApiRequest body = request.getData();
        MyWordFolder myWordFolder = myWordFolderRepository.getOne(body.getWordFolderId());
        Optional<MyWord> myWord = myWordRepository.findByMyWordFolderAndWord(myWordFolder,wordRepository.getOne(body.getWordId()));
        return myWord
                .map(myWordSelected->{
                            myWordRepository.delete(myWordSelected);
                            myWordFolder.setWordCount(myWordFolder.getWordCount()-1);
                            myWordFolderRepository.save(myWordFolder);
                            Integer userId = myWordFolderRepository.getOne(body.getWordFolderId()).getUser().getId();
                            return responseForDelete(userId,body.getWordFolderId());
                        }
                )
                .orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORD,"myword"));
    }

    private MyWordApiResponse responseForDelete(Integer userId,Integer folderId){
        List<MyWord> myWordList = myWordRepository.findAllByMyWordFolderId(folderId);
        MyWordApiResponse myWordApiResponse = MyWordApiResponse.builder()
                .userId(userId)
                .myWordFolderId(folderId)
                .previousWordCount(myWordList.size()+1)
                .nowWordCount(myWordList.size())
                .build();
        return myWordApiResponse;
    }
}
