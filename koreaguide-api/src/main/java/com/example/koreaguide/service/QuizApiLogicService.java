package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.MyWordFolder;
import com.example.koreaguide.model.entity.Word;
import com.example.koreaguide.model.enumclass.QuizType;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.MyWordFolderApiRequest;
import com.example.koreaguide.model.network.request.QuizListApiRequest;
import com.example.koreaguide.model.network.response.MyWordListApiResponse;
import com.example.koreaguide.model.network.response.QuizListApiResponse;
import com.example.koreaguide.model.network.response.QuizMultipleChoiceApiResponse;
import com.example.koreaguide.model.network.response.WordApiResponse;
import com.example.koreaguide.repository.MyWordFolderRepository;
import com.example.koreaguide.repository.MyWordRepository;
import com.example.koreaguide.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/*
 * @author : Jisoo Kim
 * @date: 2021/04/28 9:22 오후
*/
@Service
public class QuizApiLogicService {
    @Autowired
    WordRepository wordRepository;

    @Autowired
    MyWordFolderRepository myWordFolderRepository;

    @Autowired
    MyWordRepository myWordRepository;

    public QuizListApiResponse getQuizWordList(Integer id, Header<QuizListApiRequest> request) {
        QuizListApiRequest body = request.getData();
        Optional<MyWordFolder> wordFolder = myWordFolderRepository.findById(body.getFolderId());
        return wordFolder.map(selectedWordFolder->{
            List<MyWord> myWordList= myWordRepository.findAllByMyWordFolderId(selectedWordFolder.getId());
            List<Word> words = new ArrayList<Word>();
            for(int i=0;i<myWordList.size();i++){
                words.add(wordRepository.getOne(myWordList.get(i).getId()));
            }
            QuizListApiResponse response= null;
            if(!myWordList.isEmpty()){
                if(body.getQuizType()==QuizType.MATCH) {
                   response= responseMatch(words, body.getQuizType(), body.getFolderId());
                   System.out.println("DONE");
                }
            }else{
                throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_MYWORDFOLDER);
            }
            return response;
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORDFOLDER));
    }

    private QuizListApiResponse responseMatch(List<Word> myWordList, QuizType type,Integer folderId) {
        List<QuizMultipleChoiceApiResponse> quizMultipleChoiceApiResponsesList = new ArrayList<QuizMultipleChoiceApiResponse>();
        for (int i = 0; i < myWordList.size(); i++) {
            List<WordApiResponse> multipleChoiceList = new ArrayList<WordApiResponse>();
            int[] wordIndexList = getRandomIndex(myWordList.get(i).getId());
            for (int j = 0; j < wordIndexList.length; j++) {
                Word word = wordRepository.getOne(wordIndexList[j]);
                System.out.println("WORD j: "+j+" is   "+word.getWordEng());
                WordApiResponse wordApiResponse = WordApiResponse.builder()
                        .id(word.getId())
                        .wordKor(word.getWordKor())
                        .wordEng(word.getWordEng())
                        .meaningEng1(word.getMeaningEng1())
                        .meaningKor1(word.getMeaningKor1())
                        .build();
                multipleChoiceList.add(wordApiResponse);
                System.out.println("ADDED!!!!");

            }
            System.out.println("size: "+multipleChoiceList.size());
            System.out.println("size: "+multipleChoiceList.size());

            WordApiResponse thisWordApiResponse = WordApiResponse.builder()
                    .id(myWordList.get(i).getId())
                    .wordKor(myWordList.get(i).getWordKor())
                    .wordEng(myWordList.get(i).getWordEng())
                    .meaningEng1(myWordList.get(i).getMeaningEng1())
                    .meaningKor1(myWordList.get(i).getMeaningKor1())
                    .build();
            multipleChoiceList.add(thisWordApiResponse);
            Collections.shuffle(multipleChoiceList);
            System.out.println("size: "+multipleChoiceList.size());

            WordApiResponse selectedWordApiResponse = WordApiResponse.builder()
                    .wordKor(myWordList.get(i).getWordKor())
                    .id(myWordList.get(i).getId())
                    .wordEng(myWordList.get(i).getWordEng())
                    .image(myWordList.get(i).getImage()).build();

            QuizMultipleChoiceApiResponse quizMultipleChoiceApiResponse = QuizMultipleChoiceApiResponse.builder()
                    .selectedWord(selectedWordApiResponse)
                    .wordChoiceList(multipleChoiceList).build();
            quizMultipleChoiceApiResponsesList.add(quizMultipleChoiceApiResponse);
        }
        QuizListApiResponse quizListApiResponse = QuizListApiResponse.builder()
                .quizType(type)
                .folderId(folderId)
                .quizList(quizMultipleChoiceApiResponsesList).build();
        return quizListApiResponse;
    }


    private int[] getRandomIndex(Integer wordId){
        int indices[] = new int[3];
        int min = 1;
        int max = 1000;
        for(int i=0;i<3;i++){
            int selectedNumber=(int) ((Math.random() * (max - min)) + min);
            System.out.println("______SELECTED!!! : "+selectedNumber);
            while(wordRepository.findById(selectedNumber)==null || selectedNumber==wordId){
                System.out.println("______SELECTED is NULL!!! : "+selectedNumber);
                selectedNumber=(int) ((Math.random() * (max - min)) + min);
            }
            indices[i]=selectedNumber;
            for(int j=0;j<i;j++){
                if(indices[i]==indices[j]){
                    i--;
                }
            }
        }
        return indices;
    }


}
