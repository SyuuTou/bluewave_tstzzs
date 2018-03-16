package com.lhjl.tzzs.proxy.mapper;

import java.util.List;

import com.lhjl.tzzs.proxy.model.InvestorSegmentation;
import com.lhjl.tzzs.proxy.model.MetaDataSourceType;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface MetaDataSourceTypeMapper extends OwnerMapper<MetaDataSourceType> {
	/**
	 * 
	 * @return
	 */
	List<MetaDataSourceType> getEntityTest();
	  
}