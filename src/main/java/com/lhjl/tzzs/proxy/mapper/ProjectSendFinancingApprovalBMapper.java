package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectSendSearchCommenDto;
import com.lhjl.tzzs.proxy.model.ProjectSendFinancingApprovalB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface ProjectSendFinancingApprovalBMapper extends OwnerMapper<ProjectSendFinancingApprovalB> {
    Integer copyProjectSendFinancingApprovalB(ProjectSendSearchCommenDto projectSendSearchCommenDto);
}