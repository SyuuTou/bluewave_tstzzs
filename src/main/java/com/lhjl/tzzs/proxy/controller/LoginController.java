package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class LoginController {

    @PostMapping(value = "/login")
    @ResponseBody
    public CommonDto<List<String>> login(@RequestBody LoginReqBody loginReqBody){

        CommonDto<List<String>> resData = new CommonDto<List<String>>();
        List<String> data = new ArrayList<String>();
        data.add(loginReqBody.getUserName());
        resData.setData(data);

        return resData;
    }
}
