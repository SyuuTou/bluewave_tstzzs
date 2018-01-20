package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.ProjectTeamMemberWorkMapper;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberWork;
import com.lhjl.tzzs.proxy.service.ProjectTeamMemberWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class ProjectTeamMemberWorkServiceImpl implements ProjectTeamMemberWorkService {

    @Autowired
    private ProjectTeamMemberWorkMapper projectTeamMemberWorkMapper;

    @Override
    public void save(ProjectTeamMemberWork ProjectTeamMemberWork) {
        projectTeamMemberWorkMapper.insert(ProjectTeamMemberWork);
    }

    @Override
    public void deleteAll(Integer memberId) {
        ProjectTeamMemberWork projectTeamMemberWork = new ProjectTeamMemberWork();
        projectTeamMemberWork.setProjectTeamMemberId(memberId);
        projectTeamMemberWorkMapper.delete(projectTeamMemberWork);
    }

    @Override
    public int insertList(List<ProjectTeamMemberWork> projectTeamMemberWorkList) {
        return projectTeamMemberWorkMapper.insertList(projectTeamMemberWorkList);
    }

    @Override
    public void delete(ProjectTeamMemberWork projectTeamMemberWork) {
        projectTeamMemberWorkMapper.delete(projectTeamMemberWork);
    }

    @Override
    public List<ProjectTeamMemberWork> select(ProjectTeamMemberWork projectTeamMemberWork) {
        List<ProjectTeamMemberWork> projectTeamMemberWorkList =new ArrayList<>();
        projectTeamMemberWorkList = projectTeamMemberWorkMapper.select(projectTeamMemberWork);
        return projectTeamMemberWorkList;
    }
}
