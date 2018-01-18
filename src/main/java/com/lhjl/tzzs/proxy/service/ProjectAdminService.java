package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdminLogoOutputDto;

public interface ProjectAdminService {
    /**
     * 读取项目logo和其他基本信息
     * @param projectId
     * @param projectType
     * @return
     */
    CommonDto<ProjectAdminLogoOutputDto> getProjectLogoAndMainInfo(Integer projectId, Integer projectType);
}
