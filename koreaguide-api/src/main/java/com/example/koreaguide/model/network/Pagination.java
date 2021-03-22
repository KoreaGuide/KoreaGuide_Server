package com.example.koreaguide.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/*
 * @author : Jisoo Kim
 * @date: 2021/03/22 2:48 오후
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Pagination {
    private  Integer totalPages;        // 총 페이지

    private Integer totalElements;         // 총 아이템수

    private Integer currentPage;        // 현재 페이지

    private Integer currentElements;    // 현제 보여주는 아이템 수
}
