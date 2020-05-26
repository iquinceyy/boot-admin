package com.qianfeng.boot.controller.pages.back.goods;

import com.qianfeng.boot.controller.BaseController;
import com.qianfeng.boot.dao.GoodsDao;
import com.qianfeng.boot.pojo.dto.PageDTO;
import com.qianfeng.boot.pojo.dto.ResponseDTO;
import com.qianfeng.boot.pojo.entity.Goods;
import com.qianfeng.boot.pojo.query.GoodsQuery;
import com.qianfeng.boot.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/5/21 20:11
 */
@Controller
@RequestMapping("pages/back/goods")
public class GoodsController extends BaseController {

    @Resource
    GoodsDao goodsDao;
    @Resource
    GoodsService goodsService;

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
//        List<Goods>  page = goodsDao.selectPage(0,5);
//        System.out.println(page);
        System.out.println(list.size());
//        model.addAttribute("list",page);
//        model.addAttribute("list",list);
//        System.out.println(list);

        return "pages/back/goods/goodsInfo";
    }



    @RequestMapping("delete")
    String DeleteById(Long goodsId) {

        System.out.println("delete"+goodsId);

        goodsDao.deleteByPrimaryKey(goodsId);

        return "pages/back/goods/goodsList";
    }

    @RequestMapping("list")
    String list(){
        return "pages/back/goods/goodsList";
    }


    @RequestMapping("ajaxList")
    @ResponseBody
    PageDTO ajaList(GoodsQuery query){
        return goodsService.ajaxList(query);
    }

    @RequestMapping("add")
    @ResponseBody
    ResponseDTO add(Goods goods, MultipartFile pic){

        String s = saveFile(pic, "/upload/goods");
        goods.setImg(s);// 将保存图片的路径设置给这个实体类
        System.out.println("新增了一条");
        return goodsService.add(goods);
    }

    @RequestMapping("edit")
    @ResponseBody
    ResponseDTO edit(Goods goods, MultipartFile pic){

        String s = saveFile(pic, "/upload/goods");
        goods.setImg(s);// 将保存图片的路径设置给这个实体类
        System.out.println("新增了一条");
        return goodsService.edit(goods);
    }
}
