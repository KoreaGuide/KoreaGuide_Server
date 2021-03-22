package com.example.koreaguide.model.entity;

import com.example.koreaguide.model.enumclass.PlaceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:15 오후
*/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"user","place"})
@Accessors(chain = true)
public class MyPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private PlaceStatus status;

    private String diary;

    @ManyToOne
    private Place place;

    @ManyToOne User user;
}
