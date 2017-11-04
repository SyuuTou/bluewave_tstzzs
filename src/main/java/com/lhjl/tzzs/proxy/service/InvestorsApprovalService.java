package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestorsApprovalActionDto;
import com.lhjl.tzzs.proxy.dto.InvestorsApprovalDto;
import com.lhjl.tzzs.proxy.dto.TouZiDto;
import com.lhjl.tzzs.proxy.model.InvestorsApprovalNew;

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
	CommonDto<List<InvestorsApprovalNew>> findApprovals(InvestorsApprovalDto body);

	/**
	 * 后台审核操作接口
	 * @param body 请求对象
	 * @return
	 */
	CommonDto<String> approval(InvestorsApprovalActionDto body);

	/**
	 * 获取工作名片
	 * @param approvalId 投资审核记录ID
	 * @return
	 */
	CommonDto<String> getWorkcard(String approvalId);

	/**
	 * 发送认证成功和失败的模版消息
	 * @param userId
	 * @param status
	 * @param formId
	 * @return
	 */
	CommonDto<String> sendTemplate(Integer userId, Integer status, String formId);
}
