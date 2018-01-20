package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.ProjectTeamMemberCity;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberSelfteam;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface ProjectTeamMemberSelfteamService {
    void save(ProjectTeamMemberSelfteam projectTeamMemberSelfteam);

    void deleteAll(Integer memberId);

    void delete(ProjectTeamMemberSelfteam projectTeamMemberSelfteam);

    int insertList(List<ProjectTeamMemberSelfteam> projectTeamMemberSelfteamList);

    List<ProjectTeamMemberSelfteam> select(ProjectTeamMemberSelfteam projectTeamMemberSelfteam);
}
