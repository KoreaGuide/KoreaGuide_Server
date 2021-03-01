package com.example.koreaguide.ifs;


import com.example.koreaguide.model.network.Header;
import org.springframework.web.bind.annotation.RequestBody;
/*
 * @author : Jisoo Kim
 * @date: 2021/03/02 2:29 오전
*/

public interface CrudInterface<Req,Res> {
    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);

}
