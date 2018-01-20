package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.ProjectTeamMemberBusiness;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface ProjectTeamMemberBusinessService {
    void save(ProjectTeamMemberBusiness projectTeamMemberBusiness);

    void deleteAll(Integer memberId);

    void delete(ProjectTeamMemberBusiness projectTeamMemberBusiness);

    int insertList(List<ProjectTeamMemberBusiness> projectTeamMemberBusinessList);

    List<ProjectTeamMemberBusiness> select(ProjectTeamMemberBusiness projectTeamMemberBusiness);
}
