package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectSendSearchCommenDto;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberEducationB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectSendTeamMemberEducationBMapper extends OwnerMapper<ProjectSendTeamMemberEducationB> {
    List<String> findEductionList(@Param("projectSendMemberId") Integer projectSendMemberId);

    /**
     * 复制团队成员的教育经历
     * @param projectSendSearchCommenDto
     * @return
     */
    Integer copyProjectSendTeamMemberEducationB(ProjectSendSearchCommenDto projectSendSearchCommenDto);
}