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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"home"})
@Accessors(chain = true)
@Table(name = "todays_place")
public class TodaysPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate createdAt;

    @Column(name = "place1_id")
    private Integer place1Id;

    @Column(name = "place2_id")
    private Integer place2Id;

    @Column(name = "place3_id")
    private Integer place3Id;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "todaysPlace")
    private Home home;
}
