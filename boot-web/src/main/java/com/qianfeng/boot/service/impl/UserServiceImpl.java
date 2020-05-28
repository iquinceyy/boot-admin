package com.qianfeng.boot.service.impl;

import com.qianfeng.boot.dao.PermissionDao;
import com.qianfeng.boot.dao.RoleDao;
import com.qianfeng.boot.dao.UserDao;
import com.qianfeng.boot.pojo.query.UserQuery;
import com.qianfeng.boot.pojo.vo.PermissionVO;
import com.qianfeng.boot.pojo.vo.RoleVO;
import com.qianfeng.boot.pojo.vo.UserVO;
import com.qianfeng.boot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author quincey
 * Date 2020/5/21 19:52
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;
    @Resource
    PermissionDao permissionDao;
    @Resource
    RoleDao roleDao;
    @Override
    public boolean addUser() {
        return false;
    }

    @Override
    public UserVO login(UserQuery query) {
        return userDao.selectUserByPhoneAndPassword(query);
    }

    @Override
    public UserVO selectDbUserByPhone(UserQuery query) {
        UserVO userVO = userDao.selectUserByPhone(query.getPhone());
        return userVO;
    }


    @Override
    public List<RoleVO> selectHisRolesByPhone(String phone) {//通过用户名 查询 这个人的角色
        UserVO u = userDao.selectUserByPhone(phone);
        if(!StringUtils.isEmpty(u.getRoles())){
            List<RoleVO> roles = roleDao.selectHisRolesByRoles(u.getRoles());
            // 在查询完成roles之后，我们应该 roles的permissionVOS赋值
            List<PermissionVO> permissionVOS = this.selectHisPermissionByRoles(roles);//查出所有权限
            Map<Integer,List<PermissionVO>> collect = permissionVOS.stream().collect(Collectors.groupingBy(PermissionVO::getPermissionId));
            for (RoleVO r:roles){
                String permissions = r.getPermissions();
                if (!StringUtils.isEmpty(permissions)){
                    String[] split = permissions.split(",");
                    List<PermissionVO> li = new ArrayList<>();
                    for (String s :split){
                        PermissionVO p =collect.get(Integer.valueOf(s)).get(0);
                        li.add(p);
                    }
                    r.setPermissionVOS(li);
                }
            }
            return roles;
        }
        return null;
    }

     public List<PermissionVO> selectHisPermissionByRoles(List<RoleVO> roles) {
        List<PermissionVO> list = new ArrayList<>();
        //        // 第一种 ：
//        for (RoleVO role : roles) {
//            List<PermissionVO> permissionVOS = permissionDao.selectPermissionsByIds(role.getPermissions());
//            list.addAll(permissionVOS);
////            Collections.addAll(list, permissionVOS);// 这里有个把两个集合加入到一个集合里边去
//        }
        // 断言工具
//        Assert.notNull(roles, "传递的集合为null");

        if (!CollectionUtils.isEmpty(roles)){
            Set<String> paramSet = new TreeSet<>();//查询参数集合
            for(RoleVO role:roles){// 在内存之中进行的。效率基本最高的
                String psIds = role.getPermissions();// 1,2,3,4
                if (!StringUtils.isEmpty(psIds)){
                    Collections.addAll(paramSet,psIds.split(","));
                }
            }
            list = permissionDao.selectPermissionBySet(paramSet);
        }
        return list;
    }


}


















