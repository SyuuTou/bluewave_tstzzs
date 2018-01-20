package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.ProjectTeamMemberSegmentationMapper;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberSegmentation;
import com.lhjl.tzzs.proxy.service.ProjectTeamMemberSegmentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class ProjectTeamMemberSegmentationServiceImpl implements ProjectTeamMemberSegmentationService {

    @Autowired
    private ProjectTeamMemberSegmentationMapper projectTeamMemberSegmentationMapper;

    @Override
    public void save(ProjectTeamMemberSegmentation projectTeamMemberSegmentation) {
        projectTeamMemberSegmentationMapper.insert(projectTeamMemberSegmentation);
    }

    @Override
    public void deleteAll(Integer memberId) {
        ProjectTeamMemberSegmentation projectTeamMemberSegmentation = new ProjectTeamMemberSegmentation();
        projectTeamMemberSegmentation.setMemberId(memberId);
        projectTeamMemberSegmentationMapper.delete(projectTeamMemberSegmentation);
    }

    @Override
    public int insertList(List<ProjectTeamMemberSegmentation> projectTeamMemberSegmentationList) {
        return projectTeamMemberSegmentationMapper.insertList(projectTeamMemberSegmentationList);
    }

    @Override
    public void delete(ProjectTeamMemberSegmentation projectTeamMemberSegmentation) {
        projectTeamMemberSegmentationMapper.delete(projectTeamMemberSegmentation);
    }

    @Override
    public List<ProjectTeamMemberSegmentation> select(ProjectTeamMemberSegmentation projectTeamMemberSegmentation) {
        List<ProjectTeamMemberSegmentation> projectTeamMemberSegmentationList = new ArrayList<>();
        projectTeamMemberSegmentationList = projectTeamMemberSegmentationMapper.select(projectTeamMemberSegmentation);
        return projectTeamMemberSegmentationList;
    }
}
