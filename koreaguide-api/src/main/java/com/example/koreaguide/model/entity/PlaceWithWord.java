package com.example.koreaguide.model.entity;

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
import javax.persistence.OneToOne;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:18 오후
*/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"place"})
@Accessors(chain = true)
public class PlaceWithWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer contentId;

    private Integer wordCount;

    private Integer word1;

    private Integer word2;

    private Integer word3;

    private Integer word4;

    private Integer word5;

    private Integer word6;

    private Integer word7;

    private Integer word8;

    private Integer word9;

    private Integer word10;

    private Integer word11;

    private Integer word12;

    private Integer word13;

    private Integer word14;

    private Integer word15;

    private Integer word16;

    private Integer word17;

    private Integer word18;

    private Integer word19;

    private Integer word20;

    private Integer word21;

    private Integer word22;

    private Integer word23;

    private Integer word24;

    private Integer word25;

    private Integer word26;

    private Integer word27;

    private Integer word28;

    private Integer word29;

    private Integer word30;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "placeWithWord")
    private Place place;

}
