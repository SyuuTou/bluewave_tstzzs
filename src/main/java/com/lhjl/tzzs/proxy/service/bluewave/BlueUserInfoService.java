package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.bluewave.UserHeadPicOutputDto;

import java.util.Map;

public interface BlueUserInfoService {

    /**
     * 获取用户头像等信息的接口
     * @param appid
     * @param token
     * @return
     */
    CommonDto<UserHeadPicOutputDto> getUserHeadpic(Integer appid, String token);

    /**
     * 修改用户头像接口
     * @param appid
     * @param body
     * @return
     */
    CommonDto<String> updateUserHeadpic(Integer appid, Map<String,Object> body);
}
