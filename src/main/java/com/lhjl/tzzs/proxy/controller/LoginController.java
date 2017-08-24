package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;
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
    @ResponseBody
    public CommonDto<List<String>> login(@RequestBody LoginReqBody loginReqBody){





        CommonDto<List<String>> resData = commonHttpService.requestLogin(loginReqBody);

        return resData;
    }
}
