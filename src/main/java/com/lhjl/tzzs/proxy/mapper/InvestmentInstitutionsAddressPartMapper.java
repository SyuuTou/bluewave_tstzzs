package com.lhjl.tzzs.proxy.mapper;

import java.util.List;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsAddressPart;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface InvestmentInstitutionsAddressPartMapper extends OwnerMapper<InvestmentInstitutionsAddressPart> {
	/**
	 * 根据机构ID获得所有分部信息
	 * @return
	 */
	List<InvestmentInstitutionsAddressPart> findAllById(Integer investmentInstitutionId);
}