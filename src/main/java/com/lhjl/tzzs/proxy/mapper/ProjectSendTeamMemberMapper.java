package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ProjectSendTeamMember;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectSendTeamMemberMapper extends OwnerMapper<ProjectSendTeamMember> {
     void updateTeame(int  projectId);
     List<ProjectSendTeamMember> findTeam(int  projectId);
}