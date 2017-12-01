package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;

import java.util.Map;

public interface ActivityApprovalLogService {
    /**
     * 创建用户活动申请记录接口
     * @param body
     * @return
     */
    CommonDto<String> createActicityApprovalLog(Map<String,Object> body);
}
