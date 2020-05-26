package com.qianfeng.boot.controller.pages.back.goods;

import com.qianfeng.boot.dao.GoodsDao;
import com.qianfeng.boot.pojo.entity.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/5/21 20:11
 */
@Controller
@RequestMapping("pages/back/goods")
public class GoodsController {

    @Resource
    GoodsDao goodsDao;

    @RequestMapping("findById")
    Goods findById(@PathVariable Long id){

        return goodsDao.selectByPrimaryKey(id);
    }

    @RequestMapping("insert")
    Goods insert(Goods goods){

        Goods g = new Goods();
        g.setTitle("香蕉");
        goodsDao.insert(g);
        return null;
    }

    @RequestMapping("selectAll")
    Object selectAll(Model model){

        List<Goods> list = goodsDao.selectAll();
        List<Goods>  page = goodsDao.selectPage(0,5);
        System.out.println(page);
        System.out.println(list.size());
        model.addAttribute("list",page);
//        model.addAttribute("list",list);
//        System.out.println(list);

        return "pages/back/goods/goodsInfo";
    }
}
