package com.lhjl.tzzs.proxy.controller.feilu;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.service.UserEditService;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.service.common.SmsCommonService;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserEditController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserYnController.class);

    @Resource
    private UserEditService userEditService;

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Resource
    private SmsCommonService smsCommonService;


    /*
     * 设置账号密码接口
     */

    @PostMapping("user/editmessage")
    public CommonDto<UserSetPasswordOutputDto> editUserMessage(@RequestBody UserSetPasswordInputDto body){
        CommonDto<UserSetPasswordOutputDto> result = new CommonDto<>();
        UserSetPasswordOutputDto userSetPasswordOutputDto = new UserSetPasswordOutputDto();

        try {
           result=userEditService.editUserMessage(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            userSetPasswordOutputDto.setMessage("服务器端发生错误");
            userSetPasswordOutputDto.setSuccess(false);

            result.setMessage("服务器端发生错误");
            result.setStatus(50001);
            result.setData(userSetPasswordOutputDto);
        }

        return result;
    }
    /*
     * 发验证码冗余接口，用于保持和以前返回格式一致
     */
    @GetMapping("send/message")
    public CommonDto<Map<String,Object>> sendSecurityCode(String token,String phoneNum){

        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();

        try {
            result = userEditService.sendSecurityCode(token,phoneNum);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            Map<String,Object> jieguo = new HashMap<>();
            jieguo.put("message","获取验证码失败");
            jieguo.put("status",50001);
            jieguo.put("data",null);

            obj.put("jieguo",jieguo);
            obj.put("success",false);

            result.setStatus(502);
            result.setData(obj);
            result.setMessage("服务器端发生错误");

        }


        return result;

    }
    /*
     * 读用户头像姓名接口
     */
    @GetMapping("user/headpic")
    public CommonDto<Map<String,Object>> getUserHeadpic(String token){
        CommonDto<Map<String,Object>> result =new CommonDto<>();
        Map<String,Object>  obj =new HashMap<String,Object>();
        try {
            int userid =  userExistJudgmentService.getUserId(token);
            if (userid == -1){
                result.setData(null);
                result.setStatus(502);
                result.setMessage("用户token无效，请检查");
                log.info("用户token无效，请检查");

                return result;
            }
            result = userEditService.getUserHeadpic(userid);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());

            obj.put("success",false);
            obj.put("message","token非法无法获取用户头像");

            result.setMessage("token非法无法获取用户头像");
            result.setData(obj);
            result.setStatus(501);
        }

        return result;
    }

    /*
     * 用户修改头像接口
     */

    @GetMapping("user/update/headpic")
    public CommonDto<Map<String,Object>> updateUserHeadpic(String headpic,String token){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            result = userEditService.updateUserHeadpic(headpic,token);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            obj.put("success",false);
            obj.put("message","服务器发生错误");

            result.setMessage("服务器发生错误");
            result.setData(obj);
            result.setStatus(502);
        }

        return result;
    }

    /*
     * 用户修改个人信息的接口
     */
    @PostMapping("user/update/info")
    public CommonDto<Map<String,Object>> updateUserInfo(@RequestBody UsersInfoInputDto body){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();

        try {
            result = userEditService.updateUserInfo(body);

        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            obj.put("success",false);
            obj.put("message","服务器端发生错误");

            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(obj);
        }

        return result;
    }

    /*
     * 读用户信息的接口
     */
    @GetMapping("user/get/info")
    public CommonDto<Map<String,Object>> getUserInfo(String token){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            result = userEditService.getUserInfo(token);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            obj.put("message","服务器发生错误");
            obj.put("success",false);
            obj.put("data",null);

            result.setData(obj);
            result.setStatus(502);
            result.setMessage("服务器发生错误");
        }
        return result;
    }

    @GetMapping("user/infoyn")
    public CommonDto<Map<String,Object>> userInfoYn(String token){
        CommonDto<Map<String,Object>> result =new CommonDto<>();
        try {
            result = userEditService.userInfoYn(token);
        }catch (Exception e){
            log.info(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }

        return result;
    }

    @GetMapping("user/info/prefectyn")
    public CommonDto<Map<String,Object>> userInfoPerfectYn(String token){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        try {
          result = userEditService.userInfoPerfectYn(token);
        }catch (Exception e){
            log.info(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 编辑用户真实姓名，公司名称，公司职务，手机号，身份类型的接口
     * @param body
     * @return
     */
    @PostMapping("user/edit/someinfo")
    public CommonDto<String> userEditSomeinfo(@RequestBody UserEditInputDto body){
        CommonDto<String> result  =new CommonDto<>();

        try {
            result=userEditService.editSomeinfo(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
