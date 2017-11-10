package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAuditInputDto;

public interface ProjectAuditService {
    CommonDto<String> adminAuditProject(ProjectAuditInputDto body);
}
