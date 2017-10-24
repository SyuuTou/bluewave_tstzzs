package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserSetPasswordInputDto;
import com.lhjl.tzzs.proxy.dto.UserSetPasswordOutputDto;
import org.springframework.stereotype.Service;


public interface UserEditService {
    CommonDto<UserSetPasswordOutputDto> editUserPassword(UserSetPasswordInputDto body,int userid,String token);
}
