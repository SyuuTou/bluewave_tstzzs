package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectSendSearchCommenDto;
import com.lhjl.tzzs.proxy.model.ProjectSendFinancingApprovalB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

public interface ProjectSendFinancingApprovalBMapper extends OwnerMapper<ProjectSendFinancingApprovalB> {

    Integer copyProjectSendFinancingApprovalB(ProjectSendSearchCommenDto projectSendSearchCommenDto);

    ProjectSendFinancingApprovalB selectBySendProjectId(@Param("projectId") Integer projectId);
}