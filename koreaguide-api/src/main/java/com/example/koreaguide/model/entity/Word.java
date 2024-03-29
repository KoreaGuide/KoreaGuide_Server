package com.example.koreaguide.model.entity;

import com.example.koreaguide.model.enumclass.UserLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 9:00 오후
*/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = {"myWordList,homeList"})
@Accessors(chain = true)
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 전략옵션
    private Integer id;

    private String wordEng;

    private String wordKor;

    private String pronunciationEng;

    private String pronunciationKor;

    private String meaningKor1;

    private String meaningKor2;

    private String meaningEng1;

    private String meaningEng2;

    private String partOfSpeech;

    private String audio;

    private String image;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "word")
    private List<MyWord> myWordList;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "word")
    private List<Home> homeList;
}
