package com.example.koreaguide.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/01 5:57 오후
*/

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 전략옵션
    private Long id;

    private String email;

    private String password;

    private String level;

    private LocalDateTime createdAt;

    private String createdBy;

}
