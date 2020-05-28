package com.qianfeng.boot.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * Author quincey
 * Date 2020/5/28 17:00
 */
public class MySessionIdGennerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        String id = "qc"+ UUID.randomUUID();
        return id;
    }
}
