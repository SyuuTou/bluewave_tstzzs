package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;

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
}
