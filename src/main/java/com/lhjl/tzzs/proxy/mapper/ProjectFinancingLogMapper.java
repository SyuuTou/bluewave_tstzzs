package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectFinancingLogMapper extends OwnerMapper<ProjectFinancingLog> {
    List<ProjectFinancingLog> selectProjectFinancingLogList(@Param("projectId") Integer projectId,@Param("projectStatus") Integer projectStatus);
    List<String> selectInvestors(@Param("pflid") Integer pflid);
    /**
     * 获取所有的融资状态
     * @return
     */
	List<String> fetchFinancingStatus();
}