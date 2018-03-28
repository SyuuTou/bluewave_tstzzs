package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto.ProjectTeamMemberInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto.ProjectTeamMemberOutputDto;

import java.util.List;
import java.util.Map;

/**
 * Created by lanhaijulang on 2018/1/18.
 */
public interface ProjectAdminTeamService {
	
	/**
	 * 获取平台项目团队成员
	 * @param projectId 项目id
	 * @return
	 */
    CommonDto<List<ProjectTeamMemberOutputDto>> getProjectTeamMemberList(Integer subjectId,Integer subjectType);

    CommonDto<String> addOrUpdatePojectTeamMember(ProjectTeamMemberInputDto body);

    CommonDto<String> deleteProjectTeamMember(Integer memberId,Integer subjectType);

    CommonDto<Map<String,List<LabelList>>> queryHotCity();

    CommonDto<List<String>> cityElegantSearch(String searchWord);

    CommonDto<List<Map<String, Object>>> userElegantSearch(String searchWord);

}
