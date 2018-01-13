package com.lhjl.tzzs.proxy.mapper;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.ProjectInvestmentDto;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.InvestorsApproval;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface InvestorsApprovalMapper extends OwnerMapper<InvestorsApproval> {
	Map<String,Object> findInvestorsApproval(@Param("userId") Integer userId);
	List<InvestorsApproval> findApproval(@Param("checkName") String checkName,
										 @Param("time")String time, @Param("beginNum")Integer beginNum, @Param("pageSize")Integer pageSize);
	List<ProjectInvestmentDto> findApprovalName(@Param("shortName") String shortName);

	List<Map<String,Object>> findApprovalList(@Param("searchWord") String searchWord,@Param("investorsType") Integer[] investorsType,
											  @Param("approvalResult") Integer[] approvalResult,@Param("approvalTimeOrder") Integer approvalTimeOrder,
											  @Param("approvalTimeOrderDesc") Integer approvalTimeOrderDesc,@Param("startPage") Integer startPage,
											  @Param("pageSize") Integer pageSize);
	Integer findApprovalListCount(@Param("searchWord") String searchWord,@Param("investorsType") Integer[] investorsType,
							 @Param("approvalResult") Integer[] approvalResult,@Param("approvalTimeOrder") Integer approvalTimeOrder,
							 @Param("approvalTimeOrderDesc") Integer approvalTimeOrderDesc,@Param("startPage") Integer startPage,
							 @Param("pageSize") Integer pageSize);
}