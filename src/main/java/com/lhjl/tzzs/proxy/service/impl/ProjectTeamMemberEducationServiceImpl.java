package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.ProjectTeamMemberEducationMapper;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberEducation;
import com.lhjl.tzzs.proxy.service.ProjectTeamMemberEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class ProjectTeamMemberEducationServiceImpl implements ProjectTeamMemberEducationService {

    @Autowired
    private ProjectTeamMemberEducationMapper projectTeamMemberEducationMapper;

    @Override
    public void save(ProjectTeamMemberEducation projectTeamMemberEducation) {
        projectTeamMemberEducationMapper.insert(projectTeamMemberEducation);
    }

    @Override
    public void deleteAll(Integer memberId) {
        ProjectTeamMemberEducation projectTeamMemberEducation = new ProjectTeamMemberEducation();
        projectTeamMemberEducation.setProjectTeamMemberId(memberId);
        projectTeamMemberEducationMapper.delete(projectTeamMemberEducation);
    }

    @Override
    public int insertList(List<ProjectTeamMemberEducation> projectTeamMemberBusinessList) {
        return projectTeamMemberEducationMapper.insertList(projectTeamMemberBusinessList);
    }

    @Override
    public void delete(ProjectTeamMemberEducation projectTeamMemberEducation) {
        projectTeamMemberEducationMapper.delete(projectTeamMemberEducation);
    }

    @Override
    public List<ProjectTeamMemberEducation> select(ProjectTeamMemberEducation projectTeamMemberEducation) {
        List<ProjectTeamMemberEducation> projectTeamMemberEducationList = new ArrayList<>();
        projectTeamMemberEducationList = projectTeamMemberEducationMapper.select(projectTeamMemberEducation);
        return projectTeamMemberEducationList;
    }
}
