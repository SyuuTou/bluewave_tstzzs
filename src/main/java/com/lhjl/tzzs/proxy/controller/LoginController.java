package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;
import com.lhjl.tzzs.proxy.dto.SendsecuritycodeReqBody;
import com.lhjl.tzzs.proxy.dto.ZhuceReqBody;
import com.lhjl.tzzs.proxy.service.CommonHttpService;
import com.lhjl.tzzs.proxy.service.common.JedisCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
public class LoginController {

    @Resource
    private CommonHttpService commonHttpService;
    @Autowired
    private JedisCommonService jedisCommonService;

    @Autowired
    private Environment env;

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginReqBody loginReqBody){

        String  resData = commonHttpService.requestLogin(loginReqBody);

        System.out.println(env.getProperty("server.port"));
        Jedis jedis = jedisCommonService.getJedis();
        jedis.set("1111","hello world");
        System.out.println(jedis.get("1111"));

        return resData;
    }

    @PostMapping(value = "/code")
    public String code(@RequestBody SendsecuritycodeReqBody sendsecuritycodeReqBody){
        String resData = commonHttpService.requestSendsecuritycode(sendsecuritycodeReqBody);
        return resData;
    }
    @PostMapping(value = "/zhuce")
    public String zhu(@RequestBody ZhuceReqBody zhuceReqBody){
        String resData = commonHttpService.requestZhuce(zhuceReqBody);
        return resData;
    }
}
