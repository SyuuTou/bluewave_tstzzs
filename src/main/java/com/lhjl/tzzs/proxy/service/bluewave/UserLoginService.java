package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserExsitJudgmentDto;
import me.chanjar.weixin.common.exception.WxErrorException;

import java.util.Map;


public interface UserLoginService {
    /**
     * 用户登录注册接口
     * @param openId
     * @param unionid
     * @return
     */
    CommonDto<UserExsitJudgmentDto> userRegister(String openId,String unionid,Integer appid);

    /**
     * 解析用户信息，注册接口
     * @param code
     * @param appid
     * @return
     */
    CommonDto<UserExsitJudgmentDto> loginComplex(String code,Integer appid) throws WxErrorException;

    /**
     * 解析用户信息的接口
     * @param appid
     * @param body
     * @return
     */
    CommonDto<String> analysisUserInfo(Integer appid, Map<String,Object> body);

    /**
     *  根据token换取用户id的接口
     * @param token
     * @return
     */
    Integer getUserIdByToken(String token);

    /**
     * 检查用户是否有效的接口
     * @param token
     * @param appid
     * @return
     */
    CommonDto<String> checkUserToken(String token,Integer appid);

    /**
     * 检查用户sessionkey的接口
     * @param appid
     * @param sessionkey
     * @return
     */
    CommonDto<Boolean> checkUserSessionKey(Integer appid,String sessionkey,String token);
}
