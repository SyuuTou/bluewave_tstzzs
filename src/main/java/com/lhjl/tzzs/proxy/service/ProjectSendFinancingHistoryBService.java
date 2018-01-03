package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendFinancingHistoryBDto;

import java.util.List;

public interface ProjectSendFinancingHistoryBService {
    /**
     * 创建融资历史
     * @param body
     * @param appid
     * @return
     */
    CommonDto<String> creatProjectSendFinancingHistoryB(List<ProjectSendFinancingHistoryBDto> body, Integer appid);
}
