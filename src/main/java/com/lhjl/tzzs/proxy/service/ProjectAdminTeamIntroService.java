package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto.ProjectTeamIntroInputDto;

import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/6.
 */
public interface ProjectAdminTeamIntroService {

    CommonDto<String> addOrUpdatePojectTeamIntro(ProjectTeamIntroInputDto body);

    CommonDto<Map<String, Object>> getPojectTeamIntro(Integer projectId);


}
