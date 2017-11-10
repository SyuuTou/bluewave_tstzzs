package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectFinancingLogMapper extends OwnerMapper<ProjectFinancingLog> {
    List<ProjectFinancingLog> selectProjectFinancingLogList(@Param("projectId") Integer projectId,@Param("projectStatus") Integer projectStatus);
}