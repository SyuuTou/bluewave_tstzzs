package com.lhjl.tzzs.proxy.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserGetInfoDto;

import java.util.Map;

public interface UserWeixinService {
    CommonDto<UserGetInfoDto> setUsersWeixin(Integer appid, WxMaUserInfo userInfo, String userid);

    CommonDto<String> checkName(String token);

    CommonDto<String> saveFormId(Map<String, String> body);
}
