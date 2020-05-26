package com.qianfeng.boot.service;

import com.qianfeng.boot.pojo.entity.Goods;

import java.util.List;

/**
 * Author quincey
 * Date 2020/5/21 19:46
 */
public interface GoodsService {

    Goods findById(Long id);

    List<Goods> selectAll();
}
