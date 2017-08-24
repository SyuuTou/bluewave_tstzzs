package com.lhjl.tzzs.proxy.service;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;

import java.util.List;

public interface  CommonHttpService {

    List<String> requestLogin(String name, String password);

    CommonDto<List<String>> requestLogin(LoginReqBody loginReqBody);
}
