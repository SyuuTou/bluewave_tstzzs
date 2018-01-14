package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendAuditBInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAdminListInputDto;

import java.util.List;
import java.util.Map;

public interface ProjectAuditBService {
    /**
     * 读取审核项目列表
     * @return
     */
    CommonDto<Map<String,Object>> getProjectSendList(ProjectSendBAdminListInputDto body, Integer appid);

    /**
     * 审核项目信息的接口
     * @param body
     * @param appid
     * @return
     */
    CommonDto<String> auditProjectSend(ProjectSendAuditBInputDto body, Integer appid);
}
