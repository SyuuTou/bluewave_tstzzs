package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.AssessmentDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;

public interface ProjectEvaluationlogService {
	/**
	 * 保存评估记录
	 * @param params
	 * @return
	 */
	CommonDto<String> saveAssessment(AssessmentDto params);
	/**
	 * 项目评估回显
	 * @param
	 * @param
	 * @return
	 */
	 CommonDto<Map<String, Object>> findInvestorsApproval(String token);
	 /**
	 * 输入公司名称记录
	 * @param
	 * @param
	 * @return
	 */
	 CommonDto<List<Map<String, Object>>> findEvaluationLog(String token);
}
