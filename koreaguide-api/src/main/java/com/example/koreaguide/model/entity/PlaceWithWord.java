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
 * @date: 2021/03/13 11:18 오후
*/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"place"})
@Accessors(chain = true)
@Table(name = "new_mapping")
public class PlaceWithWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer placeId;

    private Integer wordId;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "placeWithWord")
    private Place place;

}
