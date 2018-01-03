package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdministratorOutputDto;
import com.lhjl.tzzs.proxy.dto.UserChooseLogDto.UserElegantServiceInputDto;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.UserInfoService;
import com.lhjl.tzzs.proxy.service.common.CommonUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public CommonDto<List<Map<String,Object>>> geyUserList(Integer pageNum,Integer pageSize){
        CommonDto<List<Map<String,Object>>> result = new CommonDto<>();

        try {
            result = userInfoService.getUserList(pageNum,pageSize);
        }catch (Exception e){
            logger.info(e.getMessage(),e.fillInStackTrace());
            List<Map<String,Object>> list = new ArrayList<>();
            result.setData(list);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }

        return result;
    }


    /**
     * 获取用户可用formid
     * @param userId 用户id
     * @return
     */
    @GetMapping("get/user/formid")
    public CommonDto<String> getUserFormid(int userId){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = userInfoService.getUserFormid(userId);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }
        
        return result;
    }


    /**
     * 设置formid为失效的接口
     * @param formid
     * @return
     */
    @GetMapping("set/user/formid")
    public CommonDto<String> setUserFormid(String formid){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = userInfoService.setUserFormid(formid);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }


    /**
     * 用token换取用户真实姓名，头像，公司名称的接口
     * @param body
     * @return
     */
    @PostMapping("get/user/complexinfo")
    public CommonDto<ProjectAdministratorOutputDto> getUserComplexInfo(@RequestBody Map<String,String> body){
        CommonDto<ProjectAdministratorOutputDto> result = new CommonDto<>();
        try {
            result = userInfoService.getUserComplexInfo(body);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误，请检查");
            result.setStatus(502);
            result.setData(null);
        }
        return result;
    }

    /**
     * 获取用户当前机构id
     * @param token
     * @return
     */
    @GetMapping("get/user/investmentInstitutionId")
    public CommonDto<Integer> getUserInvestmentInstitutionId(String token){
        CommonDto<Integer> result  = new CommonDto<>();

        try {
            result = userInfoService.getUserInvestmentInstitution(token);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
        }
        return result;
    }

    /**
     * 获取用户精选活动信息列表的接口
     * @param body
     * @return
     */
    @PostMapping("elegantservice/log/list")
    public CommonDto<Map<String,Object>> elegantServiceLogList(@RequestBody UserElegantServiceInputDto body){
        CommonDto<Map<String,Object>> result = new CommonDto<>();

        try {
            result= userInfoService.getElegantServiceLogList(body);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }

    /**
     * 设置记录的联系状态
     * @param logId
     * @return
     */
    @GetMapping("set/elegantservice/log/status")
    public CommonDto<String> setElegantServiceLogStatus(Integer logId){
        CommonDto<String> result  =new CommonDto<>();

        try {
            result = userInfoService.setElegantServiceLogStatus(logId);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }

        return result;
    }
}
