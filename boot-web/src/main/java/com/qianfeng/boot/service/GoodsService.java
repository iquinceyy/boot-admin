package com.qianfeng.boot.service;

import com.qianfeng.boot.pojo.dto.PageDTO;
import com.qianfeng.boot.pojo.dto.ResponseDTO;
import com.qianfeng.boot.pojo.entity.Goods;
import com.qianfeng.boot.pojo.query.GoodsQuery;

import java.util.List;

/**
 * Author quincey
 * Date 2020/5/21 19:46
 */
public interface GoodsService {

    Goods findById(Long id);

    List<Goods> selectAll();

    String search(String title);



    //-----------------------------------

    PageDTO ajaxList(GoodsQuery query);

    /**
     * 商品添加
     */
    ResponseDTO add(Goods goods);

    ResponseDTO edit(Goods goods);
}
