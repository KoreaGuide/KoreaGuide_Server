package com.example.koreaguide.model.entity;


import com.example.koreaguide.model.enumclass.UserLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/01 5:57 오후
*/

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = {"myWordList","myPlaceList"})
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 전략옵션
    private Integer id;

    @Column(nullable = false,unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserLevel level;

    private LocalDate lastLoginAt;

    private Integer weekAttendance;

    private LocalDateTime createdAt;

    private String createdBy;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private List<MyWord> myWordList;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private List<MyPlace> myPlaceList;
}
