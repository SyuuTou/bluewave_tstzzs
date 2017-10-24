package com.lhjl.tzzs.proxy.controller.feilu;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.service.UserEditService;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.service.common.SmsCommonService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserEditController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserYnController.class);

    @Resource
    private UserEditService userEditService;

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Resource
    private SmsCommonService smsCommonService;

    @PostMapping("user/editmessage")
    public CommonDto<UserSetPasswordOutputDto> editUserMessage(@RequestBody UserSetPasswordInputDto body){
        CommonDto<UserSetPasswordOutputDto> result = new CommonDto<>();
        UserSetPasswordOutputDto userSetPasswordOutputDto = new UserSetPasswordOutputDto();


        String securitycode = body.getSecuritycode();
        String token = body.getToken();
        String verify = body.getVerify();
        String user7realname_cn = body.getUser7realname_cn();
        String password =body.getPassword();

        if (securitycode == null || "".equals(securitycode)){
            userSetPasswordOutputDto.setMessage("验证码不能为空");
            userSetPasswordOutputDto.setSuccess(false);

            result.setMessage("验证码不能为空");
            result.setStatus(50001);
            result.setData(userSetPasswordOutputDto);

            return result;
        }

        if (token == null || "".equals(token) ){
            userSetPasswordOutputDto.setMessage("token信息不能为空");
            userSetPasswordOutputDto.setSuccess(false);

            result.setMessage("token信息不能为空");
            result.setStatus(50001);
            result.setData(userSetPasswordOutputDto);

            return result;
        }


        if (verify == null || "".equals(verify) ){
            userSetPasswordOutputDto.setMessage("手机号不能为空");
            userSetPasswordOutputDto.setSuccess(false);

            result.setMessage("手机号不能为空");
            result.setStatus(50001);
            result.setData(userSetPasswordOutputDto);

            return result;
        }


        if (user7realname_cn == null || "".equals(user7realname_cn)){
            userSetPasswordOutputDto.setMessage("真实姓名不能为空");
            userSetPasswordOutputDto.setSuccess(false);

            result.setMessage("真实姓名不能为空");
            result.setStatus(50001);
            result.setData(userSetPasswordOutputDto);

            return result;
        }

        if (password == null || "".equals(password)){
            userSetPasswordOutputDto.setMessage("密码不能为空");
            userSetPasswordOutputDto.setSuccess(false);

            result.setMessage("密码不能为空");
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

            //验证验证码是否通过
            CommonDto<String> verifyResult =  smsCommonService.verifySMS(userid,verify,securitycode);
            int verifyStatus = verifyResult.getStatus();
            if (verifyStatus ==200){
                //验证通过后的处理,账号密码，真实姓名的保存
                result = userEditService.editUserPassword(body,userId,token);

            }else {
                userSetPasswordOutputDto.setMessage(verifyResult.getMessage());
                userSetPasswordOutputDto.setSuccess(false);

                result.setMessage(verifyResult.getMessage());
                result.setStatus(verifyResult.getStatus());
                result.setData(userSetPasswordOutputDto);
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

}
