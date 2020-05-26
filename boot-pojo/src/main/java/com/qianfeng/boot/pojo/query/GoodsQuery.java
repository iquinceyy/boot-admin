package com.qianfeng.boot.pojo.query;

import com.qianfeng.boot.util.PageQuery;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Author quincey
 * Date 2020/5/26 10:31
 */
@Data
public class GoodsQuery extends PageQuery {
    private String title;// 商品名称
    private BigDecimal minPrice;//最低价格
    private BigDecimal maxPrice; // 最大价格
}
