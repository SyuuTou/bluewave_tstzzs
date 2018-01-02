package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberEducationB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectSendTeamMemberEducationBMapper extends OwnerMapper<ProjectSendTeamMemberEducationB> {
    List<String> findEductionList(@Param("projectSendMemberId") Integer projectSendMemberId);
}