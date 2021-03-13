package com.example.koreaguide.model.entity;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:06 오후
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
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"home"})
@Accessors(chain = true)
public class TodaysPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer place1Id;

    private Integer place2Id;

    private Integer place3Id;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "todaysPlace")
    private Home home;
}
