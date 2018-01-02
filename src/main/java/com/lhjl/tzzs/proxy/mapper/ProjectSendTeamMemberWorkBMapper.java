package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberWorkB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectSendTeamMemberWorkBMapper extends OwnerMapper<ProjectSendTeamMemberWorkB> {
    List<String> findWorkList(@Param("projectSendMemberId") Integer projectSendMemberId);
}