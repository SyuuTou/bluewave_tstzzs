package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.PagingOutputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendAuditBInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAdminListInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAdminListOutputDto;

import java.util.List;
import java.util.Map;

public interface ProjectAuditBService {
    /**
     * 采集项目审核列表
     * @param body
     * @param appid
     * @return
     */
    CommonDto<PagingOutputDto<ProjectSendBAdminListOutputDto>> getProjectSendList(ProjectSendBAdminListInputDto body, Integer appid);

    /**
     * 审核项目信息的接口
     * @param body
     * @param appid
     * @return
     */
    CommonDto<String> auditProjectSend(ProjectSendAuditBInputDto body, Integer appid);
}
