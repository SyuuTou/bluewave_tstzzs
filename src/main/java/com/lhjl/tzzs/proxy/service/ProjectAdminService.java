package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdminBaseInfoDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdminLogoInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdminLogoOutputDto;

public interface ProjectAdminService {
    /**
     * 读取项目logo和其他基本信息
     * @param projectId
     * @param projectType
     * @return
     */
    CommonDto<ProjectAdminLogoOutputDto> getProjectLogoAndMainInfo(Integer projectId, Integer projectType);

    /**
     * 更改项目logo和其他基本信息的接口
     * @param body
     * @return
     */
    CommonDto<String> updateProjectLogoAndMainInfo(ProjectAdminLogoInputDto body);

    /**
     * 获取项目基本信息的接口
     * @param projectId
     * @param projectType
     * @return
     */
    CommonDto<ProjectAdminBaseInfoDto> getProjectBaseInfo(Integer projectId, Integer projectType);
}
