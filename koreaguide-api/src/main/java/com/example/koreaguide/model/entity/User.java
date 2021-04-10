package com.example.koreaguide.model.entity;


import com.example.koreaguide.model.enumclass.UserLevel;
import com.example.koreaguide.model.enumclass.UserStatus;
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
@ToString(exclude = {"myPlaceList","regionColorList","testResultList"})
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

    private LocalDate lastLoginAt;

    private Integer weekAttendance;

    private LocalDateTime createdAt;

    private String createdBy;

    private UserStatus status;


    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private List<MyPlace> myPlaceList;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private List<RegionColor> regionColorList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<MyTestResult> testResultList;
}
