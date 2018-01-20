package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.ProjectTeamMemberCityMapper;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberCity;
import com.lhjl.tzzs.proxy.service.ProjectTeamMemberCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class ProjectTeamMemberCityServiceImpl implements ProjectTeamMemberCityService {

    @Autowired
    private ProjectTeamMemberCityMapper projectTeamMemberCityMapper;

    @Override
    public void save(ProjectTeamMemberCity projectTeamMemberCity) {
        projectTeamMemberCityMapper.insert(projectTeamMemberCity);
    }

    @Override
    public void deleteAll(Integer memberId) {
        ProjectTeamMemberCity projectTeamMemberCity = new ProjectTeamMemberCity();
        projectTeamMemberCity.setMemberId(memberId);
        projectTeamMemberCityMapper.delete(projectTeamMemberCity);
    }

    @Override
    public int insertList(List<ProjectTeamMemberCity> projectTeamMemberBusinessList) {
        return projectTeamMemberCityMapper.insertList(projectTeamMemberBusinessList);
    }

    @Override
    public void delete(ProjectTeamMemberCity projectTeamMemberCity) {
        projectTeamMemberCityMapper.delete(projectTeamMemberCity);
    }

    @Override
    public List<ProjectTeamMemberCity> select(ProjectTeamMemberCity projectTeamMemberCity) {
        List<ProjectTeamMemberCity> projectTeamMemberCityList = new ArrayList<>();
        projectTeamMemberCityList = projectTeamMemberCityMapper.select(projectTeamMemberCity);
        return projectTeamMemberCityList;
    }
}
