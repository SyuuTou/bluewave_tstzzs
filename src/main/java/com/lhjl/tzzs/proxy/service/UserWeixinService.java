package com.lhjl.tzzs.proxy.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserGetInfoDto;

public interface UserWeixinService {
    CommonDto<UserGetInfoDto> setUsersWeixin(WxMaUserInfo userInfo,String userid);

    CommonDto<String> checkName(String token);
}
