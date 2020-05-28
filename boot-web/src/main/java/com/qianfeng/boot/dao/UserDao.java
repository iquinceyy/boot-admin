package com.qianfeng.boot.dao;

import com.qianfeng.boot.pojo.entity.User;
import com.qianfeng.boot.pojo.vo.UserVO;

public interface UserDao {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    UserVO selectDbUserByPhone(String phone);
}