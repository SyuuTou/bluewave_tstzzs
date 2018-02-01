package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.DatasOperationManage;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface DatasOperationManageMapper extends OwnerMapper<DatasOperationManage> {
	
	/**
	 * 更新投资人的负责人
	 * @param dom
	 * @return
	 */
	Integer changeInvestorIrPrincipal(DatasOperationManage dom);
	/**
	 * 查找是否存在相应的投资人
	 * @param dom
	 * @return
	 */
	DatasOperationManage findInvestor(DatasOperationManage dom);  
}