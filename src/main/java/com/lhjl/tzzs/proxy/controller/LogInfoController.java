package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LogInfoController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LogInfoController.class);

    @PostMapping("user/set/log")
    public CommonDto<String> userSetLog(@RequestBody Map<String,Object> body){
        CommonDto<String> result =  new CommonDto<>();
        if (body.get("token") == null){
            log.info("进入记录log方法场景");
            log.info("没有传用户token");
        }

        if(body.get("behavior") ==null){
            log.info("进入记录log方法场景");
            log.info("没有传用户行为类型");
        }

        log.info("进入记录log方法场景，用户token为：{}",body.get("token"));
        log.info("用户进行了：{}",body.get("behavior"));

        result.setData(null);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }
}
