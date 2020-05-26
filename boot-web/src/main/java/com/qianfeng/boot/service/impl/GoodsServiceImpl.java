package com.qianfeng.boot.service.impl;

import com.qianfeng.boot.dao.GoodsDao;
import com.qianfeng.boot.pojo.entity.Goods;
import com.qianfeng.boot.service.GoodsService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/5/21 19:51
 */
@Controller
public class GoodsServiceImpl implements GoodsService {

    @Resource
    GoodsDao goodsDao;

    @Override
    public Goods findById(Long id) {
        return goodsDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Goods> selectAll() {
        return null;
    }
}
