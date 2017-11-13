package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ProjectFinancingHistory;
import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectFinancingHistoryMapper extends OwnerMapper<ProjectFinancingHistory> {
    List<ProjectFinancingLog> selectProjectFinancingLogList(@Param("projectId") Integer projectId, @Param("projectStatus") Integer projectStatus);
    void updateHistory (@Param("projectId") Integer projectId,@Param("userId") int userId);
}