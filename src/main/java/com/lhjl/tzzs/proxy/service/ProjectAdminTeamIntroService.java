package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TeamIntroductionDto;
import com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto.ProjectTeamIntroInputDto;

import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/6.
 */
public interface ProjectAdminTeamIntroService {

    CommonDto<String> addOrUpdatePojectTeamIntro(ProjectTeamIntroInputDto body);
    /**
     * 获取团队成员的介绍信息
     * @param projectId 主体id
     * @param subjectType 主体类型：1项目；2机构
     * @return
     */
    CommonDto<TeamIntroductionDto> getPojectTeamIntro(Integer projectId, Integer subjectType);


}
