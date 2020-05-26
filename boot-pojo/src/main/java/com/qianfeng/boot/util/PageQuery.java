package com.qianfeng.boot.util;

import lombok.Data;

/**
 * Author quincey
 * Date 2020/5/25 17:13
 */
@Data
public class PageQuery {
    private Integer page = 1;
    private Integer limit = 10;
    private Integer start = 0;// 偏移量应该计算出来

    public Integer getStart() {
        if (page != null && limit != null) {
            return (page - 1) * limit;
        }
        return start;
    }
}
