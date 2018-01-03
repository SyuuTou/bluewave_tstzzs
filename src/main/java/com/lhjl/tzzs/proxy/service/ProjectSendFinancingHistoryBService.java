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

    /**
     * 读取提交项目融资历史信息接口
     * @param projectSendBId
     * @param appid
     * @return
     */
    CommonDto<List<ProjectSendFinancingHistoryBDto>> readProjectSendFinancingHistoryB(Integer projectSendBId,Integer appid);

    /**
     * 复制提交项目融资历史的接口
     * @param newprojectSendId
     * @param projectSendBId
     * @param appid
     */
    void copyProjectSendFinancingHistoryB(Integer newprojectSendId,Integer projectSendBId,Integer appid);
}
