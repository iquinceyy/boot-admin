package com.qianfeng.boot.dao;

import com.qianfeng.boot.pojo.entity.Goods;

import java.util.List;

/**
 * Author quincey
 * Date 2020/5/21 19:45
 */
public interface GoodsDao {
    int deleteByPrimaryKey(Long orderId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> selectAll();

    List<Goods> selectPage(Integer start,Integer num);
}
