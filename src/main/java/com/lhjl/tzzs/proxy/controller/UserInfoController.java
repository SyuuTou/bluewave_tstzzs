package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.UserInfoService;
import com.lhjl.tzzs.proxy.service.common.CommonUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询个人资料
 * Created by 蓝海巨浪 on 2017/10/25.
 */
@RestController
public class UserInfoController {
    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    private CommonUserService commonUserService;
    @Resource
    private UserInfoService userInfoService;

    /**
     * 获取个人资料
     * @param token 用户token
     * @return
     */
    @GetMapping("/rest/user/newrxsdqyh")
    public CommonDto<Map<String, Object>> newrxsdqyh(String token){
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        int userID = commonUserService.getLocalUserId(token);
        try{
            result = userInfoService.newrxsdqyh(userID);
        }catch (Exception e){
            result.setStatus(5101);
            result.setMessage("查询个人资料异常");
            logger.error(e.getMessage(), e.fillInStackTrace());
        }
        return result;
    }

    @GetMapping("get/user/list")
    public CommonDto<List<Users>> geyUserList(Integer pageNum,Integer pageSize){
        CommonDto<List<Users>> result = new CommonDto<>();

        try {
            result = userInfoService.getUserList(pageNum,pageSize);
        }catch (Exception e){
            logger.info(e.getMessage(),e.fillInStackTrace());
            List<Users> list = new ArrayList<>();
            result.setData(list);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }

        return result;
    }
}
