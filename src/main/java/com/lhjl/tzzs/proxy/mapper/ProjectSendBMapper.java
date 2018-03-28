package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectSendBAuditDto.ProjectKernelInfoOutputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAuditDto.ProjectListInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendSearchDto;
import com.lhjl.tzzs.proxy.model.ProjectSendB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface ProjectSendBMapper extends OwnerMapper<ProjectSendB> {
    List<ProjectSendB> getLastCreateProject(@Param("userId") Integer userId, @Param("appid") Integer appid, @Param("prepareId") Integer prepareId);

    /**
     * 复制项目信息的查询
     * @param projectSendSearchDto
     * @return
     */
    int copyProjectSendB(ProjectSendSearchDto projectSendSearchDto);

    /**
     * 获取用户提交机构id列表
     * @param userId
     * @return
     */
    List<Integer> getUserSendInstitutionId(@Param("userId") Integer userId);

    ProjectSendB maxSerialNumber();

    /**
     * 提交项目列表
      * @param body
     * @return
     */
    List<ProjectKernelInfoOutputDto> getSendProjects(ProjectListInputDto body);

//    /**、
//     * 提交项目数目
//     * @param body
//     * @return
//     */
//    Long getSendProjectsCount(ProjectListInputDto body);
}