package com.qianfeng.boot.pojo.vo;

import com.qianfeng.boot.pojo.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * Author quincey
 * Date 2020/5/28 16:49
 */
@Data
public class RoleVO extends Role {
    List<PermissionVO> permissionVOS;
}
