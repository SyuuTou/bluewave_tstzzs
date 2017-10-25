package com.lhjl.tzzs.proxy.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.ProjectEvaluationLog;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface ProjectEvaluationLogMapper extends OwnerMapper<ProjectEvaluationLog> {
	Map<String,Object> findInvestorsApproval(@Param("userId") Integer userId);
}