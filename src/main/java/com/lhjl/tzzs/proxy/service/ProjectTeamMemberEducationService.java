package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.ProjectTeamMemberCity;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberEducation;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface ProjectTeamMemberEducationService {
    void save(ProjectTeamMemberEducation projectTeamMemberEducation);

    void deleteAll(Integer memberId);

    void delete(ProjectTeamMemberEducation projectTeamMemberEducation);

    int insertList(List<ProjectTeamMemberEducation> projectTeamMemberEducationList);

    List<ProjectTeamMemberEducation> select(ProjectTeamMemberEducation projectTeamMemberEducation);
}
