package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.service.CommonHttpService;
import com.lhjl.tzzs.proxy.service.common.JedisCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class LoginController {

    @Resource
    private CommonHttpService commonHttpService;
    @Autowired
    private JedisCommonService jedisCommonService;

    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

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

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        ResetPasswordReqBody reqBody = new ResetPasswordReqBody();
        reqBody.setMobile("13269279933");
        reqBody.setPassword("123456");
        reqBody.setPassworda("123456");
        reqBody.setSecuritycode("123456");

        HttpEntity<ResetPasswordReqBody> request = new HttpEntity<ResetPasswordReqBody>(reqBody,headers);

        String result = this.restTemplate.postForObject("https://chuangxinzhishu.wware.org/rest/user/umima.json?_ajax=true&ct=public_json",request,String.class);

//        String resData = commonHttpService.requestSendsecuritycode(sendsecuritycodeReqBody);
        return result;
    }
    @PostMapping(value = "/zhuce")
    public String zhu(@RequestBody ZhuceReqBody zhuceReqBody){
        String resData = commonHttpService.requestZhuce(zhuceReqBody);
        return resData;
    }
}
