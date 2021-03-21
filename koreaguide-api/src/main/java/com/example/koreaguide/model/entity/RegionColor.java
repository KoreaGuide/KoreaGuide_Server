package com.example.koreaguide.model.entity;

import com.example.koreaguide.model.enumclass.RegionColorStatus;
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
import javax.persistence.Table;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/21 7:06 오전
*/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"user"})
@Accessors(chain = true)
@Table(name = "region_color")
public class RegionColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer areacode;

    private RegionColorStatus regionColor;

    @ManyToOne
    private User user;
}
