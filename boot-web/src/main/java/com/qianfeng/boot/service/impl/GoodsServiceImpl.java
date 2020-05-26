package com.qianfeng.boot.service.impl;

import com.qianfeng.boot.dao.GoodsDao;
import com.qianfeng.boot.pojo.dto.PageDTO;
import com.qianfeng.boot.pojo.dto.ResponseDTO;
import com.qianfeng.boot.pojo.entity.Goods;
import com.qianfeng.boot.pojo.query.GoodsQuery;
import com.qianfeng.boot.pojo.vo.GoodsVO;
import com.qianfeng.boot.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Author quincey
 * Date 2020/5/21 19:51
 */
@Service
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


    @Override
    public PageDTO ajaxList(GoodsQuery query) {

        List<GoodsVO> list = goodsDao.ajaxList(query);
        Integer count = goodsDao.ajaxListCount(query);

        return PageDTO.setPageData(count,list);
    }

    @Override
    public ResponseDTO add(Goods goods) {

        Date date = new Date();
        goods.setUpdateTime(date);
        goods.setCreateTime(date);
        goods.setUpdateUser(date);
        goods.setMerchantId(1L);
        goods.setSold(0);
        goods.setScore(0.0);
        return ResponseDTO.get(goodsDao.insertSelective(goods)==1);
    }

    @Override
    public ResponseDTO edit(Goods goods) {
        return ResponseDTO.get(goodsDao.updateByPrimaryKeySelective(goods)==1);
    }
}
