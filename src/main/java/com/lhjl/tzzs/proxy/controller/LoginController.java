package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;
import com.lhjl.tzzs.proxy.dto.SendsecuritycodeReqBody;
import com.lhjl.tzzs.proxy.dto.ZhuceReqBody;
import com.lhjl.tzzs.proxy.service.CommonHttpService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
public class LoginController {

    @Resource
    private CommonHttpService commonHttpService;

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginReqBody loginReqBody){

        String  resData = commonHttpService.requestLogin(loginReqBody);

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
