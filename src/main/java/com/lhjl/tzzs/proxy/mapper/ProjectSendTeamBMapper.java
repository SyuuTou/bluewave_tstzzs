package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectSendSearchCommenDto;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectSendTeamBMapper extends OwnerMapper<ProjectSendTeamB> {
    List<ProjectSendTeamB> getTeamList(@Param("projectSendBId") Integer projectSendBId, @Param("appid") Integer appid);

    /**
     * 单个复制团队成员信息
     * @param projectSendSearchCommenDto
     * @return
     */
    Integer copyProjectTeamB(ProjectSendSearchCommenDto projectSendSearchCommenDto);
}