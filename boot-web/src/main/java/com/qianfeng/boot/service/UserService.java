package com.qianfeng.boot.service;

import com.qianfeng.boot.pojo.query.UserQuery;
import com.qianfeng.boot.pojo.vo.RoleVO;
import com.qianfeng.boot.pojo.vo.UserVO;

import java.util.List;

/**
 * Author quincey
 * Date 2020/5/21 19:46
 */
public interface UserService {

    boolean addUser();

    UserVO selectDbUserByPhone(UserQuery query);

    List<RoleVO> selectHisRolesByPhone(String phone);
}


















