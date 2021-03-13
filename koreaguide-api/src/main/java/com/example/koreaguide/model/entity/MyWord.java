package com.example.koreaguide.model.entity;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/13 11:09 오후
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"user","word"})
@Accessors(chain = true)
public class MyWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String wordStatus;

    @ManyToOne
    private User user;

    @ManyToOne
    private Word word;
}
