package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserSetPasswordInputDto;
import com.lhjl.tzzs.proxy.dto.UserSetPasswordOutputDto;
import com.lhjl.tzzs.proxy.dto.UsersInfoInputDto;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserEditService {
    CommonDto<UserSetPasswordOutputDto> editUserPassword(UserSetPasswordInputDto body,int userid,String token);
    CommonDto<Map<String,Object>> getUserHeadpic(int userid);
    CommonDto<Map<String,Object>> updateUserHeadpic(String headpic,String token);
    CommonDto<Map<String,Object>> sendSecurityCode(String token,String phoneNum);
    CommonDto<Map<String,Object>> updateUserInfo(UsersInfoInputDto body);
}
