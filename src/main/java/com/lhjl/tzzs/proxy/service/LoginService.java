package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;
import com.lhjl.tzzs.proxy.model.AdminUser;

/**
 * Created by lanhaijulang on 2018/2/4.
 */
public interface LoginService {

    CommonDto<AdminUser> login(LoginReqBody body,Integer appid);
}
