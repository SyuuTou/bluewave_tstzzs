package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.ProjectTeamMemberCity;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberSelfcity;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface ProjectTeamMemberSelfcityService {
    void save(ProjectTeamMemberSelfcity projectTeamMemberSelfcity);

    void deleteAll(Integer memberId);



    int insertList(List<ProjectTeamMemberSelfcity> projectTeamMemberSelfcityList);
}
