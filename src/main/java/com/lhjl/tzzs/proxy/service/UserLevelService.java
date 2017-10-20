package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.ActionDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserLevelDto;
import com.lhjl.tzzs.proxy.model.MetaUserLevel;

import java.util.List;
import java.util.Map;

/**
 * Created by 蓝海巨浪 on 2017/10/16.
 */
public interface UserLevelService {
    CommonDto<List<UserLevelDto>> findUserLevelList(String userId);
    CommonDto<UserLevelDto> findLevelInfo(String userStr, int levelId);
    CommonDto<Map<String, Object>> upLevel(String userStr, int levelId);
    CommonDto<Map<String, Object>> consume(ActionDto action);
    CommonDto<Map<String, Object>> cancel(ActionDto action);
}
