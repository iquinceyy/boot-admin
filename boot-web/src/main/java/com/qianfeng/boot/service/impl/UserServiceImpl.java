package com.qianfeng.boot.service.impl;

import com.qianfeng.boot.dao.UserDao;
import com.qianfeng.boot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/5/21 19:52
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;
    @Override
    public boolean addUser() {
        return false;
    }
}
