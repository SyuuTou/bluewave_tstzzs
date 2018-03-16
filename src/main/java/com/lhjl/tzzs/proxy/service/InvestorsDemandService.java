package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.*;

import java.util.Map;

/**
 * Created by 蓝海巨浪 on 2017/10/24.
 */
public interface InvestorsDemandService {
    /**
     * 投资偏好记录
     * @param body 请求对象
     * @return
     */
    CommonDto<String> newulingyu(Integer appId, InvestorsDemandDto body);

    /**
     * 投资偏好回显
     * @param token 用户token
     * @return
     */
    CommonDto<Map<String, Object>> newttouzilyrz(String token);

    CommonDto<Map<String,Object>> investorsDemandYn(String token);

    /**
     * 创建投资风向标记录接口
     * @param body
     * @param appid
     * @return
     */
    CommonDto<String> createInvestorsDemand(InvestorDemandInputsDto body,Integer appid);

    /**
     * 获取投资风向标/融资需求列表
     * @param body
     * @param appid
     * @return
     */
    CommonDto<Map<String,Object>> getInvestorDemand(InvestorDemandListInputDto body,Integer appid);

    /**
     * 获取是否填写完毕状态的接口
     * @param token
     * @param appid
     * @return
     */
    CommonDto<Map<String,Object>> getDemandCompeteYn(String token,Integer appid);

    /**
     * 融资需求信息回显接口/投资风向标
     * @param token
     * @param appid
     * @return
     */
    CommonDto<InvestorDemandListOutputDto> getInvestorsDemand(String token,Integer appid);

    /**
     * 后台投资风向标列表
     * @param  body
     * @param appid
     * @return
     */
    CommonDto<Map<String,Object>> getInvestorDemandList(InvestorDemandListInputDto body, Integer appid);
}
