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


        String securitycode = body.getSecuritycode();
        String token = body.getToken();
        String verify = body.getVerify();
        String user7realname_cn = body.getUser7realname_cn();
        String isWeixin = body.getIsWeixin();
        String identityType = body.getIdType();


        if ("0".equals(isWeixin)){
            if (securitycode == null || "".equals(securitycode) || "undefined".equals(securitycode)){
                userSetPasswordOutputDto.setMessage("验证码不能为空");
                userSetPasswordOutputDto.setSuccess(false);

                result.setMessage("验证码不能为空");
                result.setStatus(50001);
                result.setData(userSetPasswordOutputDto);

                return result;
            }
        }

        if (identityType == null || "".equals(identityType) || "undefined".equals(identityType)){
            userSetPasswordOutputDto.setMessage("身份类型不能为空");
            userSetPasswordOutputDto.setSuccess(false);

            result.setMessage("身份类型不能为空");
            result.setStatus(50001);
            result.setData(userSetPasswordOutputDto);

            return result;
        }

        if (token == null || "".equals(token) || "undefined".equals(token) ){
            userSetPasswordOutputDto.setMessage("token信息不能为空");
            userSetPasswordOutputDto.setSuccess(false);

            result.setMessage("token信息不能为空");
            result.setStatus(50001);
            result.setData(userSetPasswordOutputDto);

            return result;
        }


        if (verify == null || "".equals(verify) || "undefined".equals(verify)){
            userSetPasswordOutputDto.setMessage("手机号不能为空");
            userSetPasswordOutputDto.setSuccess(false);

            result.setMessage("手机号不能为空");
            result.setStatus(50001);
            result.setData(userSetPasswordOutputDto);

            return result;
        }


        if (user7realname_cn == null || "".equals(user7realname_cn) || "undefined".equals(user7realname_cn)){
            userSetPasswordOutputDto.setMessage("真实姓名不能为空");
            userSetPasswordOutputDto.setSuccess(false);

            result.setMessage("真实姓名不能为空");
            result.setStatus(50001);
            result.setData(userSetPasswordOutputDto);

            return result;
        }


        try {

            //先获取到用户id
            int userida =  userExistJudgmentService.getUserId(token);

            if (userida == -1){
                userSetPasswordOutputDto.setMessage("token非法，没有找到用户");
                userSetPasswordOutputDto.setSuccess(false);

                result.setMessage("token非法，没有找到用户");
                result.setStatus(502);
                result.setData(userSetPasswordOutputDto);

                return result;
            }
            String userid = String.valueOf(userida);
            int userId =userida;

            //获取到的如果是微信的手机号，就不再验证验证码了，否则验证验证码
            if ("0".equals(isWeixin)) {
                //验证验证码是否通过
                CommonDto<String> verifyResult = smsCommonService.verifySMS(userid, verify, securitycode);
                int verifyStatus = verifyResult.getStatus();
                if (verifyStatus == 200) {
                    //验证通过后的处理,账号密码，真实姓名的保存
                    result = userEditService.editUserPassword(body, userId, token);

                } else {
                    userSetPasswordOutputDto.setMessage(verifyResult.getMessage());
                    userSetPasswordOutputDto.setSuccess(false);

                    result.setMessage(verifyResult.getMessage());
                    result.setStatus(verifyResult.getStatus());
                    result.setData(userSetPasswordOutputDto);
                }
            }else if("1".equals(isWeixin)){
                result = userEditService.editUserPassword(body,userId,token);
            }else{
                result.setStatus(50001);
                result.setMessage("isWeixin参数错误");
                result.setData(null);

                return result;

            }
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

}
