package com.example.koreaguide.model.entity;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:11 오후
*/

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
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"placeKorean","placeWithWord","myPlaceList"})
@Accessors(chain = true)
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String address1;

    private String address2;

    private Integer contentId;

    private Integer areaCode;

    private Integer sigunguCode;

    private String firstImage;

    private String fistImage2;

    private String mapX;

    private String mapY;

    private String overview;

    private String cat1;

    private String cat2;

    private String cat3;

    @OneToOne
    private PlaceKorean placeKorean;

    @OneToOne
    private PlaceWithWord placeWithWord;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "place")
    private List<MyPlace> myPlaceList;

}
