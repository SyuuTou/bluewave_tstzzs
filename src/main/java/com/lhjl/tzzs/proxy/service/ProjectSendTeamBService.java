package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendTeamBDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendTeamBOutDto;

import java.util.List;

public interface ProjectSendTeamBService {
    /**
     * 创建/更新团队成员信息接口
     * @param body
     * @param appid
     * @return
     */
    CommonDto<String> createTeamMember(ProjectSendTeamBDto body,Integer appid);

    /**
     * 根据提交项目团队成员id获取团队成员信息接口
     * @param projectSendMemberId 团队成员id
     * @return
     */
    CommonDto<ProjectSendTeamBDto> readTeamMemberById(Integer projectSendMemberId,Integer appid);

    /**
     * 读取提交项目团队成员列表接口
     * @param projectSendBId
     * @return
     */
    CommonDto<List<ProjectSendTeamBOutDto>> readTeamMemberList(Integer projectSendBId,Integer appid);

    /**
     * 删除提交项目团队成员信息接口
     * @param projectSendMemberId
     * @param appid
     * @return
     */
    CommonDto<String> deleteTeamMemberById(Integer projectSendMemberId,Integer appid);

    /**
     * 复制团队成员的接口
     * @param appid
     * @param projectSendBId
     */
    void copyProjectSendBTeam(Integer appid,Integer projectSendBId,Integer newprepareid);
}
