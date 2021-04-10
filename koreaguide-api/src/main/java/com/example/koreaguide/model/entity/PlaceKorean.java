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
import javax.persistence.Table;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:17 오후
*/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"place"})
@Accessors(chain = true)
@Table(name = "final_place_korean")
public class PlaceKorean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer contentId;

    private String korOverview;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "placeKorean")
    private Place place;
}
