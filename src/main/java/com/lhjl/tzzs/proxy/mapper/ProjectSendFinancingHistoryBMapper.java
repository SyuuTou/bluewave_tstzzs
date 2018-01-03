package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectSendSearchCommenDto;
import com.lhjl.tzzs.proxy.model.ProjectSendFinancingHistoryB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface ProjectSendFinancingHistoryBMapper extends OwnerMapper<ProjectSendFinancingHistoryB> {
    /**
     * 复制项目融资历史的sql
     * @param projectSendSearchCommenDto
     * @return
     */
    Integer copyProjectSendFinancingHistory(ProjectSendSearchCommenDto projectSendSearchCommenDto);
}