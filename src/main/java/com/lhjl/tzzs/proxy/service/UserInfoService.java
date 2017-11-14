package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.Users;

import java.util.List;
import java.util.Map;

/**
 * Created by 蓝海巨浪 on 2017/10/25.
 */
public interface UserInfoService {

    /**
     * 获取个人资料
     * @param userId 用户ID
     * @return
     */
    CommonDto<Map<String, Object>> newrxsdqyh(int userId);

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    CommonDto<List<Map<String,Object>>> getUserList(Integer pageNum, Integer pageSize);

    /**
     * 获取用户可用formid
     * @param userId 用户id
     * @return
     */
    CommonDto<String> getUserFormid(Integer userId);

    /**
     * 设置formid为失效的接口
     * @param formid
     * @return
     */
    CommonDto<String> setUserFormid(String formid);
}
