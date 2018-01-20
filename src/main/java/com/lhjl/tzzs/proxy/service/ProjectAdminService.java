package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.*;

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

    /**
     * 更新项目基本信息的接口
     * @param body
     * @return
     */
    CommonDto<String> updateProjectBaseInfo(ProjectAdminBaseInfoInputDto body);
}
