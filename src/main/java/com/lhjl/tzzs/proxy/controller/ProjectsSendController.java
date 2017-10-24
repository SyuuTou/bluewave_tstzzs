package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectsSendDto;
import com.lhjl.tzzs.proxy.service.ProjectsSendService;
import com.lhjl.tzzs.proxy.service.common.CommonUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 项目投递
 * Created by 蓝海巨浪 on 2017/10/23.
 */
@RestController
public class ProjectsSendController {
    private static Logger logger = LoggerFactory.getLogger(ProjectsSendController.class);

    @Resource
    private ProjectsSendService projectsSendService;
    @Resource
    private CommonUserService commonUserService;


    /**
     * 项目投递
     */
    @PostMapping("/rest/zyy/ctuisongsecond")
    public CommonDto<String> ctuisongsecond(@RequestBody ProjectsSendDto params){
        CommonDto<String> result = new CommonDto<>();

        try{
            //获取userId
            int userId = commonUserService.getLocalUserId(params.getToken());

            result = projectsSendService.ctuisongsecond(params, userId);

        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("项目投递异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    /**
     * 项目投递回显
     */
    @GetMapping("/rest/zyy/rtuisonghuixian")
    public CommonDto<Map<String, Object>> rtuisonghuixian(String token, String tsid){
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        try{
            //获取userId
            int userId = commonUserService.getLocalUserId(token);
            result = projectsSendService.rtuisonghuixian(userId, tsid);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("投递项目回显异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 融资历史记录
     */
    @PostMapping("/rest/zyy/ctuisongthird")
    public CommonDto<String> ctuisongthird(@RequestBody Map<String, String> body){
        CommonDto<String> result = new CommonDto<>();
        String tsid = body.get("tsid");
        String rongzilishi = body.get("rongzilishi");
        try{
            result = projectsSendService.ctuisongthird(tsid, rongzilishi);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("融资历史记录异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 融资历史回显
     */
    @GetMapping("/rest/zyy/rtuisongthird")
    public CommonDto<Map<String, Object>> rtuisongthird(String tsid){
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        try{
            result = projectsSendService.rtuisongthird(tsid);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("融资历史回显异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }


}
