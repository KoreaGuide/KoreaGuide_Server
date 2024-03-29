package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.Word;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.WordApiRequest;
import com.example.koreaguide.model.network.response.WordApiResponse;
import com.example.koreaguide.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordApiLogicService {
    @Autowired
    WordRepository wordRepository;

    public WordApiResponse getWordInfo(Integer wordId) {
        Optional<Word> word = wordRepository.findById(wordId);
        return word.map(selectedWord->{
            return response(selectedWord);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_WORD));

    }

    private WordApiResponse response(Word selectedWord) {
        WordApiResponse wordApiResponse = WordApiResponse.builder()
                .id(selectedWord.getId())
                .wordKor(selectedWord.getWordKor())
                .wordEng(selectedWord.getWordEng())
                .meaningKor1(selectedWord.getMeaningKor1())
                .meaningKor2(selectedWord.getMeaningKor2())
                .meaningEng2(selectedWord.getMeaningEng2())
                .meaningEng1(selectedWord.getMeaningEng1())
                .pronunciationEng(selectedWord.getPronunciationEng())
                .audio(selectedWord.getAudio())
                .image(selectedWord.getImage())
                .build();
        return wordApiResponse;
    }
}
