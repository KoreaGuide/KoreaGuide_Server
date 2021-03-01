package com.example.koreaguide.model.network.request;

import com.example.koreaguide.ifs.CrudInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/02 2:42 오전
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApiRequest{

    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String level;
}
