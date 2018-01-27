package com.lhjl.tzzs.proxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsAddressPart;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface InvestmentInstitutionsAddressPartMapper extends OwnerMapper<InvestmentInstitutionsAddressPart> {
	/**
	 * 根据机构ID获得所有分部信息
	 * @return
	 */
	List<InvestmentInstitutionsAddressPart> findAllById(Integer investmentInstitutionId);
	/**
	 * 根据sort字段排序获取所有的分部信息
	 * @param companyId
	 * @return
	 */
	List<InvestmentInstitutionsAddressPart> selectAllByDefaultSort(@Param("companyId")Integer companyId);
}