package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.ProjectTeamMemberSelfteamMapper;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberSelfteam;
import com.lhjl.tzzs.proxy.service.ProjectTeamMemberSelfteamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class ProjectTeamMemberSelfteamServiceImpl implements ProjectTeamMemberSelfteamService {

    @Autowired
    private ProjectTeamMemberSelfteamMapper projectTeamMemberSelfteamMapper;

    @Override
    public void save(ProjectTeamMemberSelfteam projectTeamMemberSelfteam) {
        projectTeamMemberSelfteamMapper.insert(projectTeamMemberSelfteam);
    }

    @Override
    public void deleteAll(Integer memberId) {
        ProjectTeamMemberSelfteam projectTeamMemberSelfteam = new ProjectTeamMemberSelfteam();
        projectTeamMemberSelfteam.setMemberId(memberId);
        projectTeamMemberSelfteamMapper.delete(projectTeamMemberSelfteam);
    }

    @Override
    public int insertList(List<ProjectTeamMemberSelfteam> projectTeamMemberSelfteamList) {
        return projectTeamMemberSelfteamMapper.insertList(projectTeamMemberSelfteamList);
    }

    @Override
    public void delete(ProjectTeamMemberSelfteam projectTeamMemberSelfteam) {
        projectTeamMemberSelfteamMapper.delete(projectTeamMemberSelfteam);
    }

    @Override
    public List<ProjectTeamMemberSelfteam> select(ProjectTeamMemberSelfteam projectTeamMemberSelfteam) {
        List<ProjectTeamMemberSelfteam> projectTeamMemberSelfteamList = new ArrayList<>();
        projectTeamMemberSelfteamList = projectTeamMemberSelfteamMapper.select(projectTeamMemberSelfteam);
        return projectTeamMemberSelfteamList;
    }
}
