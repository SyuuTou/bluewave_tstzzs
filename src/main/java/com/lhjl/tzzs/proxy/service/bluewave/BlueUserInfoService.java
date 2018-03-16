package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.bluewave.UserHeadPicOutputDto;
import com.lhjl.tzzs.proxy.dto.bluewave.UserInfomationInputDto;
import com.lhjl.tzzs.proxy.dto.bluewave.UserInformationOutputDto;
import com.lhjl.tzzs.proxy.model.MetaIdentityType;

import java.util.List;
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

    /**
     * 获取用户信息接口
     * @param appid
     * @param token
     * @return
     */
    CommonDto<UserInformationOutputDto> getUserInformation(Integer appid,String token);

    /**
     * 获取用户身份类型元数据信息
     * @return
     */
    CommonDto<List<MetaIdentityType>> getMetaIdentityType();

    /**
     * 编辑用户信息的接口
     * @param body
     * @return
     */
    CommonDto<String> editUserInfo(UserInfomationInputDto body,Integer appid);

    /**
     * 检查手机号接口
     * @param token
     * @param appid
     * @return
     */
    CommonDto<Integer> checkUserPonenumber(String token,Integer appid);

    /**
     * 检查用户头像的接口
     * @param token
     * @param appid
     * @return
     */
    CommonDto<Integer> checkUserHeadpic(String token,Integer appid);
}
