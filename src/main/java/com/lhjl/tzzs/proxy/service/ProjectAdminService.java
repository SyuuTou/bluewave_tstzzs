package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.*;

public interface ProjectAdminService {
	/**
     * 读取主体(项目或者机构)logo和其他基本信息
     * @param subjectId 主体id 
     * @param subjectType 主体类型
     * @return
     */
    CommonDto<ProjectAdminLogoOutputDto> getProjectLogoAndMainInfo(Integer subjectId, Integer subjectType);

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
    /**
     * 更新项目评级
     * @param appid
     * @param body
     * @return
     */
//	CommonDto<Boolean> updateSubjectRate(Integer appid, SubjectRateInputDto body);
}
