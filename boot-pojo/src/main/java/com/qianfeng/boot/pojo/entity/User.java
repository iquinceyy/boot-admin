package com.qianfeng.boot.pojo.entity;

import lombok.Data;

/**
 * Author quincey
 * Date 2020/5/21 19:41
 */
@Data
public class User {
    private Long userId;
    private String name;
    private Integer age;
    private String sex;
    private String interest;

}
