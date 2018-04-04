package com.lhjl.tzzs.proxy.mapper;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.ProjectInvestmentDto;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestorsApprovalInputDto;
import com.lhjl.tzzs.proxy.dto.InvestorsApprovalOutputDto;
import com.lhjl.tzzs.proxy.model.InvestorsApproval;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface InvestorsApprovalMapper extends OwnerMapper<InvestorsApproval> {
	/**
	 * 获取用户的当前的审核记录
	 * @param userId 用户id
	 * @return
	 */
	Map<String,Object> findInvestorsApproval(@Param("userId") Integer userId);
	List<InvestorsApproval> findApproval(@Param("checkName") String checkName,
										 @Param("time")String time, @Param("beginNum")Integer beginNum, @Param("pageSize")Integer pageSize);
	List<ProjectInvestmentDto> findApprovalName(@Param("shortName") String shortName);
	
	/**
	 * 投资人提交认证审核列表
	 * @param body
	 * @return
	 */
	List<InvestorsApprovalOutputDto> findApprovalList(InvestorsApprovalInputDto body);
	/**
	 * 投资人认证审核列表总数
	 * @param body
	 * @return
	 */
	Long findApprovalListCount(InvestorsApprovalInputDto body);
}