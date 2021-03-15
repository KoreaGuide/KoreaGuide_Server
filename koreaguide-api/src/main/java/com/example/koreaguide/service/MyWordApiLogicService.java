package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.entity.Word;
import com.example.koreaguide.model.enumclass.MyWordStatus;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.MyWordApiRequest;
import com.example.koreaguide.model.network.response.MyWordApiResponse;
import com.example.koreaguide.model.network.response.MyWordListApiResponse;
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

    public MyWordApiResponse addWordToMyWordList(Integer id, Header<MyWordApiRequest> request) {
        //request에 있는 id 갖는 word 찾아서
        MyWordApiRequest body = request.getData();
        Optional<Word> selectedWord = wordRepository.findById(body.getWordId());
        return selectedWord.map(word->{
            Optional<User> user = userRepository.findById(id);
            MyWord newMyWord=user.map(selectedUser->{
                if(myWordRepository.findByUserAndWord(selectedUser,word).isPresent()){
                    // 이미 해당 단어를 mywordlist에 해당 유저가 갖고있을때
                    System.out.println("ALREADY HAVE!!!");
                    System.out.println("________________________");
                    System.out.println("selected user: "+selectedUser+"   word:"+word);
                    System.out.println("THIS!!!  "+myWordRepository.findByUserAndWord(selectedUser,word));
                    throw new KoreaGuideException(KoreaGuideError.DUPLICATE_ERROR_MYWORD,"Already exists");
                }
                MyWord myWord = MyWord.builder()
                        .word(word)
                        .user(selectedUser)
                        .wordStatus(MyWordStatus.NO_STATUS).build();
                return myWordRepository.save(myWord);
                })
                    .orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER,"user"));
            System.out.println("MY NEW WORD: "+newMyWord);
            return responseForAdd(newMyWord);})
                .orElseThrow(()->
                        new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_WORD,"word")
                );

    }
    private MyWordApiResponse responseForAdd(MyWord myWord){
        List<MyWord> myWordList = myWordRepository.findAllByUserId(myWord.getUser().getId());
        System.out.println("WORD LIST!!! "+myWordList);
        MyWordApiResponse myWordApiResponse = MyWordApiResponse.builder()
                .userId(myWord.getUser().getId())
                .previousWordCount(myWordList.size()-1)
                .nowWordCount(myWordList.size())
                .build();
        return myWordApiResponse;
    }

    private MyWordApiResponse response(MyWord myword){
        List<MyWord> myWordList = myWordRepository.findAllByUserId(myword.getUser().getId());
        System.out.println("MY WORD LIST = "+myWordList);
        List<MyWordListApiResponse> myWordListApiResponseList =new ArrayList<MyWordListApiResponse>();
        for(int i=0;i<myWordList.size();i++){
            MyWordListApiResponse myWordListApiResponse =MyWordListApiResponse.builder()
                    .id(myWordList.get(i).getWord().getId())
                    .word_eng(myWordList.get(i).getWord().getWordEng())
                    .word_kor(myWordList.get(i).getWord().getWordKor())
                    .meaning_eng(myWordList.get(i).getWord().getMeaningEng())
                    .meaning_kor(myWordList.get(i).getWord().getMeaningKor())
                    .image(myWordList.get(i).getWord().getImage())
                    .audio(myWordList.get(i).getWord().getAudio())
                    .pronunciation(myWordList.get(i).getWord().getPronunciation())
                    .level(myWordList.get(i).getWord().getLevel())
                    .myWordStatus(myWordList.get(i).getWordStatus())
                    .build();
            myWordListApiResponseList.add(myWordListApiResponse);
        }
        System.out.println("WORD API RESPONSE LIST = "+myWordListApiResponseList);
        MyWordApiResponse myWordApiResponse = MyWordApiResponse.builder()
                .userId(myword.getUser().getId())
                .nowWordCount(myWordList.size())
                .myWordList(myWordListApiResponseList)
                .build();
        return myWordApiResponse;
    }


    public MyWordApiResponse getMyWordList(Integer id) {
        List<MyWord> myWordList = myWordRepository.findAllByUserId(id);
        if(myWordList.isEmpty()){
            throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_MYWORD,"myWordList");
        }
        System.out.println("MY WORD LIST"+myWordList);
        return response(myWordList.get(0));
    }

    public MyWordApiResponse deleteMyWord(Integer id,Header<MyWordApiRequest> request) {
        MyWordApiRequest body = request.getData();
        Optional<MyWord> myWord = myWordRepository.findByUserAndWord(userRepository.getOne(id),wordRepository.getOne(body.getWordId()));
        return myWord
                .map(myWordSelected->{
                            myWordRepository.delete(myWordSelected);
                            return responseForDelete(id);
                        }
                )
                .orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORD,"myword"));
    }

    private MyWordApiResponse responseForDelete(Integer userId){
        List<MyWord> myWordList = myWordRepository.findAllByUserId(userId);
        MyWordApiResponse myWordApiResponse = MyWordApiResponse.builder()
                .userId(userId)
                .previousWordCount(myWordList.size()+1)
                .nowWordCount(myWordList.size())
                .build();
        return myWordApiResponse;
    }
}
