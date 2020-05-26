package com.qianfeng.boot.controller.pages.back.user;

import com.qianfeng.boot.dao.GoodsDao;
import com.qianfeng.boot.dao.UserDao;
import com.qianfeng.boot.pojo.entity.Goods;
import com.qianfeng.boot.pojo.entity.User;
import com.qianfeng.boot.pojo.query.UserQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Author quincey
 * Date 2020/5/21 20:11
 */
@Controller
@RequestMapping("/pages/back/user")
public class UserController {

    @Resource
    UserDao userDao;
    @Resource
    GoodsDao goodsDao;
    String insertUser(){
        User user = new User();
        user.setName("李四");
//        user.setAge(18);
//        user.setInterest("学习");
//        userDao.
        return null;
    }

    @RequestMapping("getUserById")
    String getUserById() {
        System.out.println("lalala111");
        return "pages/back/user/userInfo";
    }

    @RequestMapping("getUserById2")
    String test(Model model, User u, HttpServletRequest request) {
        model.addAttribute("name","张三");

        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("test","测试");

        // 1、传递简单基本类型，字符串，数字，Long,double````
        request.getSession().setAttribute("nickNmae","Hello ");

        // 2、传递一个对象
        u.setName("张三");
        u.setAge(18);
        u.setSex("男");
        u.setInterest("学习");

        model.addAttribute("u",u);

        // 3、向页面传递集合或者数组
        List<User> list = new ArrayList<>();
        list.add(u);
        list.add(u);
        list.add(u);
        list.add(u);

        model.addAttribute("list",list);

        // 4、传递一个日期到前端去被格式化
        model.addAttribute("date",new Date());

        // 5、掌握text和utext的区别
        model.addAttribute("text","<h1>text和utext的区别</h1>");




        System.out.println("lalala222");
        return "pages/back/user/userInfo";
    }


    @RequestMapping("userList")
    String userList() {

        System.out.println("userList");
        return "pages/back/user/userList";

    }

    // 只要是ajax请求，都应该 以ajax命名开头
    @RequestMapping("ajaxUserList")
    @ResponseBody
    Object ajaxUserList(UserQuery query,Model model) {

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("code", 0);
        resMap.put("msg", "查询成功");

//        List<UserVO> list = new ArrayList<>();
//        UserVO u = new UserVO();
//        u.setAge(18);
//        u.setUserId(2222L);
//        u.setName("张三");
//        list.add(u);
//        list.add(u);
//        list.add(u);

        List<Goods> goods = goodsDao.selectAll();
        System.out.println(goods);
        resMap.put("data", goods);
//        resMap.put("data", list);
        resMap.put("count", 100);
        return resMap;
    }

    @RequestMapping("delete")
    String DeleteById(Long goodsId) {

        System.out.println("delete"+goodsId);

        goodsDao.deleteByPrimaryKey(goodsId);

        return "pages/back/user/userList";
    }

    @RequestMapping("add")
    String addGoods(Goods goods) {

        System.out.println("add....."+goods);

//        Goods g = new Goods();
//        g.setTitle("葡萄2");
//        g.setGoodsId(44L);
//        goodsDao.insert(g);
        goodsDao.insert(goods);

        return "pages/back/user/userList";
    }

}
