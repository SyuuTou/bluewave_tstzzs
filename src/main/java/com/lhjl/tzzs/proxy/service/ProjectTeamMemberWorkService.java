package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.ProjectTeamMemberCity;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberWork;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface ProjectTeamMemberWorkService {
    void save(ProjectTeamMemberWork projectTeamMemberWork);

    void deleteAll(Integer memberId);

    void delete(ProjectTeamMemberWork projectTeamMemberWork);

    int insertList(List<ProjectTeamMemberWork> projectTeamMemberWorkList);

    List<ProjectTeamMemberWork> select(ProjectTeamMemberWork projectTeamMemberWork);
}
