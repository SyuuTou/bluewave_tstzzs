package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectSendSearchCommenDto;
import com.lhjl.tzzs.proxy.model.ProjectSendInvestorB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface ProjectSendInvestorBMapper extends OwnerMapper<ProjectSendInvestorB> {
    /**
     * 复制融资历史投资方信息
     * @param projectSendSearchCommenDto
     * @return
     */
    Integer copyProjectSendInvestorB(ProjectSendSearchCommenDto projectSendSearchCommenDto);
}