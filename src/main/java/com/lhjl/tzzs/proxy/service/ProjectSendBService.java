package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.PagingOutputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAuditDto.ProjectKernelInfoOutputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAuditDto.ProjectListInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAuditDto.ProjectLogoInfoOutputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBOutDto;

import java.util.List;

public interface ProjectSendBService {

    /**
     * 获取perpareid方法
     * @param token 用户token
     * @param appid 应用id
     * @return
     */
    Integer getPrepareId(String token ,Integer appid);

    /**
     * 创建项目信息接口
     * @param body
     * @param appid
     * @return
     */
    CommonDto<String> updateProject(ProjectSendBDto body,Integer appid);

    /**
     * 项目信息回显接口
     * @param token
     * @param appid
     * @return
     */
    CommonDto<ProjectSendBOutDto> readProjectInfomation(String token,Integer appid);

    /**
     * 复制项目信息接口
     * @param prepareid
     * @param newprepareid
     * @param appid
     * @param userId
     * @param projectSendBId
     * @return
     */
    CommonDto<Integer> copyProject(Integer prepareid,Integer newprepareid,Integer appid,Integer userId,Integer projectSendBId);

    /**
     * 读取提交项目基本信息的接口
     * @param projectSendId
     * @param appid
     * @return
     */
    CommonDto<ProjectLogoInfoOutputDto> readProjectSendBLogoInfo(Integer projectSendId,Integer appid);

    /**
     * 小程序提交项目列表
     * @param appid
     * @param body
     * @return
     */
    CommonDto<PagingOutputDto<ProjectKernelInfoOutputDto>> listProjectInfos(Integer appid,  ProjectListInputDto body);

    CommonDto<List<ProjectSendBOutDto>> readProjectInfomationList(String token, Integer appId, Integer pageNo, Integer pageSize);

    CommonDto<ProjectSendBOutDto> readProjectInfomation(String token, Integer projectId, Integer appid);

    CommonDto<String> updateProjectById(ProjectSendBDto body, Integer appId);

    CommonDto<String> commitProjectAudit(ProjectSendBDto body, Integer appid);
}
