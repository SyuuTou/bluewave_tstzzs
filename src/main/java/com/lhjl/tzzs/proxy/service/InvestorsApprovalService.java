package com.lhjl.tzzs.proxy.service;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TouZiDto;

public interface InvestorsApprovalService {
	/**
	 * 保存认证信息
	 * @param params
	 * @return
	 */
	CommonDto<String> saveTouZi(TouZiDto params);

	/**
	 * 认证信息的回显
	 * @param token
	 * @return
	 */
	 CommonDto<Map<String, Object>> findInvestorsApproval(String token);

	/**
	 * 审核状态
	 * @param token
	 * @return
	 */
	CommonDto<Map<String,Object>> findInvestorsExamine(String token);
}
