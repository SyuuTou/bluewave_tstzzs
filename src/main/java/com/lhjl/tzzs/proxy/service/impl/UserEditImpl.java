package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserSetPasswordInputDto;
import com.lhjl.tzzs.proxy.dto.UserSetPasswordOutputDto;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.UserEditService;
import com.lhjl.tzzs.proxy.utils.MD5Util;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserEditImpl implements UserEditService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserEditImpl.class);

    @Resource
    private UsersMapper usersMapper;

    @Override
    public CommonDto<UserSetPasswordOutputDto> editUserPassword(UserSetPasswordInputDto body,int userId,String token){
        CommonDto<UserSetPasswordOutputDto> result =new CommonDto<>();
        UserSetPasswordOutputDto userSetPasswordOutputDto = new UserSetPasswordOutputDto();


        String verify = body.getVerify();
        String user7realname_cn = body.getUser7realname_cn();
        String password = body.getPassword();




        Users users = new Users();
        users.setActualName(user7realname_cn);
        users.setPhonenumber(verify);
        String passwordForSet = encodePassword(password);
        users.setPassword(passwordForSet);
        users.setId(userId);

        usersMapper.updateByPrimaryKeySelective(users);

        userSetPasswordOutputDto.setSuccess(true);
        userSetPasswordOutputDto.setMessage(null);
        userSetPasswordOutputDto.setHaspassword(true);
        userSetPasswordOutputDto.setToken(token);
        userSetPasswordOutputDto.setYhid(String.valueOf(userId));

        result.setStatus(200);
        result.setData(userSetPasswordOutputDto);
        result.setMessage("success");


        return result;
    }

    //加密算法
    private String encodePassword(String password){
        String result = "";

       String password1 =  MD5Util.md5Encode(password,"utf-8");
       String tmpString = password1.substring(2,7);
       String password2 = password1 + tmpString;
       String finalPassword = MD5Util.md5Encode(password2,"utf-8");

       result = finalPassword;

        return result;
    }
}
