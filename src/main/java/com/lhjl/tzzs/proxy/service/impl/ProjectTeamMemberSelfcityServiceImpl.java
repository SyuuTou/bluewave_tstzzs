package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.ProjectTeamMemberSelfcityMapper;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberSelfcity;
import com.lhjl.tzzs.proxy.service.ProjectTeamMemberSelfcityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class ProjectTeamMemberSelfcityServiceImpl implements ProjectTeamMemberSelfcityService {

    @Autowired
    private ProjectTeamMemberSelfcityMapper projectTeamMemberSelfcityMapper;

    @Override
    public void save(ProjectTeamMemberSelfcity projectTeamMemberSelfcity) {
        projectTeamMemberSelfcityMapper.insert(projectTeamMemberSelfcity);
    }

    @Override
    public void deleteAll(Integer memberId) {
        ProjectTeamMemberSelfcity projectTeamMemberSelfcity = new ProjectTeamMemberSelfcity();
        projectTeamMemberSelfcity.setMemberId(memberId);
        projectTeamMemberSelfcityMapper.delete(projectTeamMemberSelfcity);
    }

    @Override
    public int insertList(List<ProjectTeamMemberSelfcity> projectTeamMemberSelfcityList) {
        return projectTeamMemberSelfcityMapper.insertList(projectTeamMemberSelfcityList);
    }

}
