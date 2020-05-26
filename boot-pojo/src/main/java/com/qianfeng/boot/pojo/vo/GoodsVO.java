package com.qianfeng.boot.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qianfeng.boot.pojo.entity.Goods;
import lombok.Data;

import java.util.Date;

/**
 * Author quincey
 * Date 2020/5/22 20:48
 */
@Data
public class GoodsVO extends Goods {

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 更新人
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateUser;

    private Integer pageSize;

    private Integer total;

}
