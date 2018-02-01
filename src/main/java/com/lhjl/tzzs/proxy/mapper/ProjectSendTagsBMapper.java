package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectSendSearchCommenDto;
import com.lhjl.tzzs.proxy.model.ProjectSendTagsB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectSendTagsBMapper extends OwnerMapper<ProjectSendTagsB> {

    /**
     * 复制提交项目标签信息
     * @param projectSendSearchCommenDto
     * @return
     */
    Integer copyProjectSendTagsB(ProjectSendSearchCommenDto projectSendSearchCommenDto);

    List<ProjectSendTagsB> selectByProjectId(@Param("projectId") Integer projectId);
}