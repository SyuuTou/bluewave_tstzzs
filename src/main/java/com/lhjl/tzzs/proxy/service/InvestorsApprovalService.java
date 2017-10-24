package com.lhjl.tzzs.proxy.service;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TouZiDto;

public interface InvestorsApprovalService {
	CommonDto<String> saveTouZi(TouZiDto params);
	 CommonDto<Map<String, Object>> findInvestorsApproval(String userID);
	CommonDto<Map<String,Object>> findInvestorsExamine(String userID);
}
