package com.example.koreaguide.model.entity;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 8:52 오후
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"todaysPlace","word"})
@Data
@Accessors(chain = true)
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 전략옵션
    private Integer id;

    private String level;

    private LocalDate createdAt;

    @OneToOne
    private TodaysPlace todaysPlace;

    @ManyToOne Word word;
}
