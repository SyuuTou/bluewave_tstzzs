package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.ProjectTeamMemberBusinessMapper;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberBusiness;
import com.lhjl.tzzs.proxy.service.ProjectTeamMemberBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class ProjectTeamMemberBusinessServiceImpl implements ProjectTeamMemberBusinessService {

    @Autowired
    private ProjectTeamMemberBusinessMapper projectTeamMemberBusinessMapper;

    @Override
    public void save(ProjectTeamMemberBusiness projectTeamMemberBusiness) {
        projectTeamMemberBusinessMapper.insert(projectTeamMemberBusiness);
    }

    @Override
    public void deleteAll(Integer memberId) {
        ProjectTeamMemberBusiness projectTeamMemberBusiness = new ProjectTeamMemberBusiness();
        projectTeamMemberBusiness.setMemberId(memberId);
        projectTeamMemberBusinessMapper.delete(projectTeamMemberBusiness);
    }

    @Override
    public int insertList(List<ProjectTeamMemberBusiness> projectTeamMemberBusinessList) {
        return projectTeamMemberBusinessMapper.insertList(projectTeamMemberBusinessList);
    }

    @Override
    public void delete(ProjectTeamMemberBusiness projectTeamMemberBusiness) {
        projectTeamMemberBusinessMapper.delete(projectTeamMemberBusiness);
    }

    @Override
    public List<ProjectTeamMemberBusiness> select(ProjectTeamMemberBusiness projectTeamMemberBusiness) {
        List<ProjectTeamMemberBusiness> projectTeamMemberBusinessList = new ArrayList<>();
        projectTeamMemberBusinessList = projectTeamMemberBusinessMapper.select(projectTeamMemberBusiness);
        return projectTeamMemberBusinessList;
    }
}
