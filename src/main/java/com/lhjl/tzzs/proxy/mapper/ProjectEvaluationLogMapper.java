package com.lhjl.tzzs.proxy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.ProjectEvaluationLog;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface ProjectEvaluationLogMapper extends OwnerMapper<ProjectEvaluationLog> {
	Map<String,Object> findInvestorsApproval(@Param("userId") Integer userId);
	List<Map<String, Object>> findEvaluationLog(@Param("userId") Integer userId);
	
}