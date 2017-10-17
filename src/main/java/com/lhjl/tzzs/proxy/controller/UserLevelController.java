package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.ActionDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserLevelDto;
import com.lhjl.tzzs.proxy.service.UserLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员中心
 * Created by 蓝海巨浪 on 2017/10/16.
 */
@RestController
public class UserLevelController {
    private Logger logger = LoggerFactory.getLogger(UserLevelController.class);

    @Resource
    private UserLevelService userLevelService;

    /**
     * 获取会员等级信息
     * @param userId 用户ID
     * @return
     */
    @GetMapping("/finduserlevel/{userId}")
    public CommonDto<List<UserLevelDto>> findUserLevel(@PathVariable String userId){
        CommonDto<List<UserLevelDto>> result = new CommonDto<List<UserLevelDto>>();
        try{
            result = userLevelService.findUserLevelList(userId);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("查询会员信息异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    /**
     * 进入会员购买页
     * @param body 请求对象
     * @return
     */
    @PostMapping("/buylevel")
    public CommonDto<UserLevelDto> userLevelForBuy(@RequestBody ActionDto body){
        CommonDto<UserLevelDto> result = new CommonDto<UserLevelDto>();
        try{
            result = userLevelService.findLevelInfo(body);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("查询会员信息异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 升级会员等级（包括购买会员）
     * @param body 请求对象
     * @return
     */
    @PostMapping("/uplevel")
    public CommonDto<String> upLevel(@RequestBody ActionDto body){
        CommonDto<String> result = new CommonDto<String>();
        try{
            result = userLevelService.upLevel(body);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("查询会员信息异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 消费金币
     * @param body 请求对象
     * @return
     */
    @PostMapping("/consume")
    public CommonDto<String> consumeCoin(@RequestBody ActionDto body){
        CommonDto<String> result = new CommonDto<String>();
        try{
            result = userLevelService.consume(body);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("查询会员信息异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

}
