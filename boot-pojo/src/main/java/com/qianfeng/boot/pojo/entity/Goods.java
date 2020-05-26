package com.qianfeng.boot.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Author quincey
 * Date 2020/5/21 19:41
 */
@Data
public class Goods implements Serializable {
    /**
     * 这个是跟后边的分布式雪花算法做铺垫的：
     */

    private Long goodsId;

    /**
     * 商品类型：
     */
    private String title;

    /**
     * 商品原价：
     */
    private BigDecimal oldPrice;

    /**
     * 商品现价：
     */
    private BigDecimal nowPrice;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private Date updateUser;

    /**
     * 已经售数量
     */
    private Integer sold;

    /**
     * 商品评分
     */
    private Double score;

    /**
     * 这个商品是哪个商户的，外键
     */
    private Long merchantId;

    /**
     * 商品的图片地址
     */
    private String img;

    private static final long serialVersionUID = 1L;
}
