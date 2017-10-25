package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.ActionDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserLevelDto;

import java.util.List;
import java.util.Map;

/**
 * Created by 蓝海巨浪 on 2017/10/16.
 */
public interface UserLevelService {
    /**
     * 查找会员等级信息
     * @param userId 用户ID
     * @return
     */
    CommonDto<List<UserLevelDto>> findUserLevelList(String userId);

    /**
     * 进入会员等级购买页
     * @param userStr 用户ID（字符串）
     * @param levelId 当前页面会员等级
     * @return
     */
    CommonDto<UserLevelDto> findLevelInfo(String userStr, int levelId);

    /**
     * 会员升级
     * @param userStr 用户ID（字符串）
     * @param levelId 要升级的会员等级
     * @return
     */
    CommonDto<Map<String, Object>> upLevel(String userStr, int levelId);

    /**
     * 消费金币
     * @param action 请求对象
     * @return
     */
    CommonDto<Map<String, Object>> consume(ActionDto action);

    /**
     * 用户取消消费提示
     * @param userId 用户ID
     * @param sceneKey 场景KEY
     * @return
     */
    CommonDto<Map<String, Object>> cancel(String userId, String sceneKey);

    /**
     * 支付完成之后调用
     * @param userId 用户ID（本系统）
     * @param status 支付状态
     * @return
     */
    CommonDto<String> changeLevel(Integer userId, int status);
}
