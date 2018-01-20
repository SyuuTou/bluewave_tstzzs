package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.ProjectTeamMemberCity;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberSegmentation;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface ProjectTeamMemberSegmentationService {
    void save(ProjectTeamMemberSegmentation projectTeamMemberSegmentation);

    void deleteAll(Integer memberId);

    void delete(ProjectTeamMemberSegmentation projectTeamMemberSegmentation);

    int insertList(List<ProjectTeamMemberSegmentation> projectTeamMemberSegmentationList);

    List<ProjectTeamMemberSegmentation> select(ProjectTeamMemberSegmentation projectTeamMemberSegmentation);
}
