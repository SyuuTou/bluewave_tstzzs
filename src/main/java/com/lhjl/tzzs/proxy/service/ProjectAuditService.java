package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAuditInputDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ProjectAuditService {
    CommonDto<String> adminAuditProject(ProjectAuditInputDto body);
}
