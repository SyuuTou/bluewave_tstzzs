package com.lhjl.tzzs.proxy.mapper;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsProject;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface InvestmentInstitutionsProjectMapper extends OwnerMapper<InvestmentInstitutionsProject> {
	/**
	 * 更新融资历史阶段对应机构的单条的状态
	 * @param id 主键id
	 * @return
	 */
	Boolean updateDelStatus(@Param("projectId") Integer projectId ,@Param("investmentInstitutionsId") Integer investmentInstitutionsId);
	
	/**
	 * 更新相关的信息
	 * @param body
	 */
	Boolean updateLogRelativeInvestmentInfo(InvestmentInstitutionsProject body);
}