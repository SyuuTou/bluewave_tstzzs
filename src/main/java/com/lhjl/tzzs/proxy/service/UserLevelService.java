package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.ActionDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InterviewInputDto;
import com.lhjl.tzzs.proxy.dto.UserLevelDto;

import java.util.List;
import java.util.Map;

/**
 * Created by 蓝海巨浪 on 2017/10/16.
 */
public interface UserLevelService {
    /**
     * 查找会员等级信息
     *
     * @param appId
     * @param userId 用户ID
     * @return
     */
    CommonDto<List<UserLevelDto>> findUserLevelList(Integer appId, String userId);

    /**
     * 进入会员等级购买页
     * @param userStr 用户ID（字符串）
     * @param levelId 当前页面会员等级
     * @param appId
     * @return
     */
    CommonDto<UserLevelDto> findLevelInfo(String userStr, int levelId, Integer appId);

    /**
     * 会员升级
     *
     * @param id
     * @param userStr 用户ID（字符串）
     * @param levelId 要升级的会员等级
     * @param presentedType
     * @return
     */
    CommonDto<Map<String, Object>> upLevel(Integer id, String userStr, int levelId, String presentedType, Integer appId);

    /**
     * 消费金币提醒
     * @param action 请求对象
     * @param appId
     * @return
     */
    CommonDto<Map<String, Object>> consumeTips(ActionDto action, Integer appId);
    /**
     * 消费金币
     * @param action 请求对象
     * @param appId
     * @return
     */
    CommonDto<Map<String, Object>> consume(ActionDto action, Integer appId);

    /**
     * 用户取消消费提示
     * @param userId 用户ID
     * @param sceneKey 场景KEY
     * @param appId
     * @return
     */
    CommonDto<Map<String, Object>> cancel(String userId, String sceneKey, Integer appId);

    /**
     * 支付完成之后调用
     * @param userId 用户ID（本系统）
     * @param status 支付状态
     * @param appId
     * @return
     */
    CommonDto<String> changeLevel(Integer userId, int status, Integer appId);

    /**
     * 获取当前获取当前用户有效等级，有多个显示等级最高的那个
     * @param token
     * @param appId
     * @return
     */
    CommonDto<Map<String,Object>> getUserLevel(String token, Integer appId);

    /**
     * 信息流里查看约谈的提示接口
     * @param body
     * @param appId
     * @return
     */
    CommonDto<Map<String,Object>> interviewTips(InterviewInputDto body, Integer appId);

    /**
     * 信息流里查看约谈的消费接口
     * @param body
     * @param appId
     * @return
     */
    CommonDto<Map<String,Object>> interviewCost(InterviewInputDto body, Integer appId);
}
