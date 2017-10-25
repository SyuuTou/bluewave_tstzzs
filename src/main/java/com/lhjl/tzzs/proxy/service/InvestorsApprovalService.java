package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestorsApprovalActionDto;
import com.lhjl.tzzs.proxy.dto.InvestorsApprovalDto;
import com.lhjl.tzzs.proxy.dto.TouZiDto;
import com.lhjl.tzzs.proxy.model.InvestorsApproval;

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

	/**
	 * 获取投资审核信息
	 * @param body 查询条件
	 * @return
	 */
	CommonDto<List<InvestorsApproval>> findApprovals(InvestorsApprovalDto body);

	/**
	 * 后台审核操作接口
	 * @param body 请求对象
	 * @return
	 */
	CommonDto<String> approval(InvestorsApprovalActionDto body);
}
