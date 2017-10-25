package com.lhjl.tzzs.proxy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.InvestorsApproval;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface InvestorsApprovalMapper extends OwnerMapper<InvestorsApproval> {
	Map<String,Object> findInvestorsApproval(@Param("userId") Integer userId);
	List<InvestorsApproval> findApproval(@Param("checkName") String checkName,
										 @Param("time")String time, @Param("beginNum")Integer beginNum, @Param("pageSize")Integer pageSize);
}