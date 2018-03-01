package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorKernelInfoDto;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public interface InvestorInfoService {
	/**
	 * 保存或者更新投资人头部信息
	 * @param body
	 * @return
	 */
    CommonDto<Integer> addOrUpdateInvestorInfo(InvestorKernelInfoDto body);

    CommonDto<InvestorKernelInfoDto> getInvestorInfo(Integer investorId);
}
