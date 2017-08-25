package com.lhjl.tzzs.proxy.dto;

import java.util.UUID;

/**
 * Description:
 * User: 罗智文
 * Date: 2017-08-25
 * Time: 13:42
 */
public class Guid {
    public String app_key;

    //获取随机key
    public String guid(){
        UUID uuid = UUID.randomUUID();
        String key = uuid.toString();
        return key;
    }
    //获取唯一标识符
    public String App_key(){
        Guid g = new Guid();
        String guid = g.guid();
        app_key = guid;
        return app_key;
    }

    public static void  main(String[] args) {
        Guid gd = new Guid();
        String app_key =  gd.App_key();
        System.out.println("app_key:"+app_key);
    }
}
