package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ProjectSendTeamMember;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectSendTeamMemberMapper extends OwnerMapper<ProjectSendTeamMember> {
    void updateTeame(@Param("projectId") int  projectId, @Param("userId") int userId);
    List<ProjectSendTeamMember> findTeam(@Param("projectId")int  projectId,@Param("userId") int userId);
}