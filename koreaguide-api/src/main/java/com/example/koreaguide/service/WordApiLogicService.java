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

    public WordApiResponse getWordInfo(Header<WordApiRequest> request) {
        WordApiRequest body = request.getData();
        Optional<Word> word = wordRepository.findById(body.getWordId());
        return word.map(selectedWord->{
            return response(selectedWord);
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_WORD));

    }

    private WordApiResponse response(Word selectedWord) {
        WordApiResponse wordApiResponse = WordApiResponse.builder()
                .id(selectedWord.getId())
                .word_kor(selectedWord.getWordKor())
                .word_eng(selectedWord.getWordEng())
                .meaning_kor(selectedWord.getMeaningKor())
                .meaning_eng(selectedWord.getMeaningEng())
                .pronunciation(selectedWord.getPronunciation())
                .audio(selectedWord.getAudio())
                .image(selectedWord.getImage())
                .build();
        return wordApiResponse;
    }
}
