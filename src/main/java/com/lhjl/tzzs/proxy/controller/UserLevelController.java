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
import java.util.Map;

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
    @PostMapping("/finduserlevel")
    public CommonDto<List<UserLevelDto>> findUserLevel(@RequestBody String userId){
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
        String userStr = body.getUserId();
        int levelId = body.getLevelId();
        try{
            result = userLevelService.findLevelInfo(userStr, levelId);
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
    public CommonDto<Map<String, Object>> upLevel(@RequestBody ActionDto body){
        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
        String userStr = body.getUserId();
        int levelId = body.getLevelId();
        try{
            result = userLevelService.upLevel(userStr, levelId);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("升级会员异常");
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
    public CommonDto<Map<String, Object>> consumeCoin(@RequestBody ActionDto body){
        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
        try{
            result = userLevelService.consume(body);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("会员消费异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 用户取消消费提示
     * @param body 请求对象
     * @return
     */
    @PostMapping("/cancel")
    public CommonDto<Map<String, Object>> cancelTips(@RequestBody ActionDto body){
        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
        String userId = body.getUserId();
        String sceneKey = body.getSceneKey();
        try{
            result = userLevelService.cancel(userId, sceneKey);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("用户取消消费提示异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }


    /**
     * 测试支付完成之后的流程
     * @param body
     * @return
     */
//    @PostMapping("/payafter")
//    public CommonDto<Map<String, Object>> testPayAfter(@RequestBody Map<String, Object> body){
//        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
//        int userId = (int)body.get("userId");
//        int status = (int)body.get("status");
//        try{
//            result = userLevelService.changeLevel(userId, status);
//        }catch(Exception e){
//            result.setStatus(501);
//            result.setMessage("用户取消消费提示异常");
//            logger.error(e.getMessage(),e.fillInStackTrace());
//        }
//        return result;
//    }
}
