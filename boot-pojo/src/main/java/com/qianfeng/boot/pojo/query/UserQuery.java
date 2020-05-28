package com.qianfeng.boot.pojo.query;

import com.qianfeng.boot.util.PageQuery;
import lombok.Data;

/**
 * Author quincey
 * Date 2020/5/25 17:14
 */
@Data
public class UserQuery extends PageQuery {
    private String password;
    private String phone;
}
