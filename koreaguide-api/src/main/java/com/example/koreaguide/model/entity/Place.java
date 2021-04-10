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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"placeKorean","placeWithWord","myPlaceList"})
@Accessors(chain = true)
@Table(name = "final_place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String address1;

    private Integer areaCode;

    private Integer sigunguCode;

    private String firstImage;

    private String firstImage2;

    @Column(name = "map_x")
    private String mapX;

    @Column(name = "map_y")
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
