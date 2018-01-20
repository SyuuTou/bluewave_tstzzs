package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.ProjectTeamMemberStageMapper;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberSelfteam;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberStage;
import com.lhjl.tzzs.proxy.service.ProjectTeamMemberStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class ProjectTeamMemberStageServiceImpl implements ProjectTeamMemberStageService {

    @Autowired
    private ProjectTeamMemberStageMapper projectTeamMemberStageMapper;

    @Override
    public void save(ProjectTeamMemberStage projectTeamMemberStage) {
        projectTeamMemberStageMapper.insert(projectTeamMemberStage);
    }

    @Override
    public void deleteAll(Integer memberId) {
        ProjectTeamMemberStage projectTeamMemberStage = new ProjectTeamMemberStage();
        projectTeamMemberStage.setMemberId(memberId);
        projectTeamMemberStageMapper.delete(projectTeamMemberStage);
    }

    @Override
    public int insertList(List<ProjectTeamMemberStage> projectTeamMemberStageList) {
        return projectTeamMemberStageMapper.insertList(projectTeamMemberStageList);
    }

    @Override
    public void delete(ProjectTeamMemberStage projectTeamMemberStage) {
        projectTeamMemberStageMapper.delete(projectTeamMemberStage);
    }

    @Override
    public List<ProjectTeamMemberStage> select(ProjectTeamMemberStage projectTeamMemberStage) {
        List<ProjectTeamMemberStage> projectTeamMemberStageList = new ArrayList<>();
        projectTeamMemberStageList = projectTeamMemberStageMapper.select(projectTeamMemberStage);
        return projectTeamMemberStageList;
    }
}
