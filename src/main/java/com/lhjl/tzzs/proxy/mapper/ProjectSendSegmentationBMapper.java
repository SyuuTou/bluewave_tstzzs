package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectSendSearchCommenDto;
import com.lhjl.tzzs.proxy.model.ProjectSendSegmentationB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectSendSegmentationBMapper extends OwnerMapper<ProjectSendSegmentationB> {
    /**
     * 复制提交项目领域信息sql
     * @param projectSendSearchCommenDto
     * @return
     */
    Integer copyProjectSendSegmentationB(ProjectSendSearchCommenDto projectSendSearchCommenDto);

    List<ProjectSendSegmentationB> selectByProjectId(@Param("projectId") Integer projectId);
}