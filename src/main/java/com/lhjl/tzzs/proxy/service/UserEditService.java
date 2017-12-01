package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.*;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserEditService {
    CommonDto<UserSetPasswordOutputDto> editUserPassword(UserSetPasswordInputDto body,int userid,String token);
    CommonDto<Map<String,Object>> getUserHeadpic(int userid);
    CommonDto<Map<String,Object>> updateUserHeadpic(String headpic,String token);
    CommonDto<Map<String,Object>> sendSecurityCode(String token,String phoneNum);
    CommonDto<Map<String,Object>> updateUserInfo(UsersInfoInputDto body);
    CommonDto<Map<String,Object>> getUserInfo(String token);
    CommonDto<Map<String,Object>> userInfoYn(String token);
    CommonDto<Map<String,Object>> userInfoPerfectYn(String token);
    CommonDto<UserSetPasswordOutputDto> editUserMessage(UserSetPasswordInputDto body);

    /**
     * 编辑用户真实姓名，公司名称，公司职务，手机号，身份类型的接口
     * @param body
     * @return
     */
    CommonDto<String> editSomeinfo(UserEditInputDto body);
}
