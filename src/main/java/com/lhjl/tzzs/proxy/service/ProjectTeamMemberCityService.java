package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.ProjectTeamMemberBusiness;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberCity;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface ProjectTeamMemberCityService {
    void save(ProjectTeamMemberCity projectTeamMemberCity);

    void deleteAll(Integer memberId);

    void delete(ProjectTeamMemberCity projectTeamMemberCity);

    int insertList(List<ProjectTeamMemberCity> projectTeamMemberCityList);

    List<ProjectTeamMemberCity> select(ProjectTeamMemberCity projectTeamMemberCity);
}
