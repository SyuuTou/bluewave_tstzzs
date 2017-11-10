package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ProjectFinancingHistory;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectFinancingHistoryMapper extends OwnerMapper<ProjectFinancingHistory> {
    void updateHistory(int projectId);
}