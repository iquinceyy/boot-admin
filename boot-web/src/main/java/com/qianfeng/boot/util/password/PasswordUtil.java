package com.qianfeng.boot.util.password;

/**
 * Author quincey
 * Date 2020/5/28 19:40
 */
public class PasswordUtil {

    private final  static String SALT = "suan_ge";
    public static String encodePassword(String password){
        String salt = "{{"+SALT+"}}"; //颜值准备好
        MD5Code md5Code = new MD5Code();
        String md5ofStr = md5Code.getMD5ofStr(salt + password);
        for (int i = 0; i < 3; i++) {
             md5ofStr = md5Code.getMD5ofStr(md5ofStr);
        }
        return md5ofStr;
    }
}
