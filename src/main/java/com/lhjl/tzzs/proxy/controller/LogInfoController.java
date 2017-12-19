package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.LogInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class LogInfoController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LogInfoController.class);

    @Resource
    private LogInfoService logInfoService;

    @PostMapping("user/set/log")
    public CommonDto<String> userSetLog(@RequestBody Map<String,Object> body){
        CommonDto<String> result =  new CommonDto<>();
        if (body.get("token") == null){
            log.info("进入记录log方法场景,没有传用户token");
        }

        if(body.get("behavior") ==null){
            log.info("进入记录log方法场景,没有传用户行为类型");
        }

        log.info("进入记录log方法场景，用户token为：{}, 场景：{}",body.get("token"),body.get("behavior"));

        try {
            //记录log

            String token = (String)body.get("token");
            String behavior = (String)body.get("behavior");

           result = logInfoService.saveUserInfo(token,behavior,"oldscene");
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }


        return result;
    }

    /**
     * 记录用户操作记录的信息
     * @param token 用户token
     * @param data 对应数据
     * @param sceneKey 场景
     * @return
     */
    @GetMapping("user/save/log")
    public CommonDto<String> userSaveLog(String token,String data,String sceneKey){
        CommonDto<String> result = new CommonDto<>();

        if (token == null){
            log.info("进入记录log方法场景,没有传用户token");
        }else {
            log.info("进入记录log方法场景,用户的token为：{}",token);
        }

        if (data == null){
            log.info("进入记录log方法场景,当前操作没有输入数据");
        }else {
            log.info("进入记录log方法场景,用户输入的数据为：{}",data);
        }

        if (sceneKey == null){
            log.info("前端没有传入操作类型");
        }else {
            log.info("用户进行了：{}操作",sceneKey);
        }


        try {
            result = logInfoService.saveUserInfo(token,data,sceneKey);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());

            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }
        return result;
    }

    /**
     * 记录精选活动操作记录接口
     * @param body
     * @return
     */
    @PostMapping("set/elegantservice/log")
    public CommonDto<String> setElegantServiceLog(@RequestBody Map<String,Object> body){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = logInfoService.saveElegantServiceLog(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }
}
