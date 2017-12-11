package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ProjectSegmentation;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectSegmentationMapper extends OwnerMapper<ProjectSegmentation> {
    List<ProjectSegmentation> findProjectSegmentation(@Param("projectId") Integer projectId);
}