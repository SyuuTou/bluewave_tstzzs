package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.ProjectTeamMemberCity;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberStage;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface ProjectTeamMemberStageService {
    void save(ProjectTeamMemberStage projectTeamMemberStage);

    void deleteAll(Integer memberId);

    void delete(ProjectTeamMemberStage projectTeamMemberStage);

    int insertList(List<ProjectTeamMemberStage> projectTeamMemberStageList);

    List<ProjectTeamMemberStage> select(ProjectTeamMemberStage projectTeamMemberStage);
}
