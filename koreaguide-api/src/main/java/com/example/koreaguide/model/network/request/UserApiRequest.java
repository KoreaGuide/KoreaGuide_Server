package com.example.koreaguide.model.network.request;

import com.example.koreaguide.ifs.CrudInterface;
import com.example.koreaguide.model.enumclass.UserLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/02 2:42 오전
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApiRequest{

    private Integer id;

    @Email
    private String email;

    private String password;

    private String nickname;
}
