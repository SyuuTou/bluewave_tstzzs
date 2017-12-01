package com.lhjl.tzzs.proxy.controller.feilu;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserYnDto;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.service.UserInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class UserYnController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserYnController.class);

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("useryn")
    public CommonDto<UserYnDto> userYn(String token){
        CommonDto<UserYnDto> result = new CommonDto<>();

        try {
            result = userExistJudgmentService.userYn(token);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(501);
            result.setMessage("服务器发生错误");
        }

        return result;
    }

    /**
     * 判断用户是否报名活动的接口
     * @param token
     * @return
     */
    @GetMapping("usera/activity/yn")
    public CommonDto<String> getUserActivityYn(String token){
        CommonDto<String> result  = new CommonDto<>();

        try {
            result = userInfoService.getUserActivity(token);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 活动申请页面信息回显接口
     * @param token
     * @return
     */
    @GetMapping("user/activity/info")
    public CommonDto<Map<String,Object>> userActicityInfo(String token){
        CommonDto<Map<String,Object>> result = new CommonDto<>();

        try {
            result = userInfoService.getUserActivityInfo(token);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }
}
