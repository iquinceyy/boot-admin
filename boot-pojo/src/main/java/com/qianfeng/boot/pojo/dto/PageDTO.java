package com.qianfeng.boot.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author quincey
 * Date 2020/5/26 12:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    private Integer code = 0;

    private String msg = "";

    private Integer count = 0;

    private Object data;

    public static PageDTO setPageData(Integer count, Object data) {

        return new PageDTO(0, "分页成功", count, data);
    }
}
