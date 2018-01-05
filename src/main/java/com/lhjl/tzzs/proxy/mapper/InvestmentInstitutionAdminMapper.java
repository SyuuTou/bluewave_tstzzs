package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionAdmin;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface InvestmentInstitutionAdminMapper extends OwnerMapper<InvestmentInstitutionAdmin> {
	/**
	 * 根据用户id获取机构id
	 * @param userId
	 * @return
	 */
	Integer findInvestmentInstitionIdByUserId(Integer userId);
}