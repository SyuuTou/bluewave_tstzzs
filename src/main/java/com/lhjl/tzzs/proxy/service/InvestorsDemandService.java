package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestorDemandInputsDto;
import com.lhjl.tzzs.proxy.dto.InvestorsDemandDto;

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
    CommonDto<String> newulingyu(InvestorsDemandDto body);

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
}
