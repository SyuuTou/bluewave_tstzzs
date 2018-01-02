package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBOutDto;

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

    CommonDto<String> copyProject(Integer prepareid,Integer newprepareid,Integer appid,Integer userId,Integer projectSendBId);

}
