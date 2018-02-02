package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorInvestInfoDto;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public interface InvestorInvestInfoService {

    CommonDto<String> addOrUpdateInvestorInvestInfo(InvestorInvestInfoDto body);

    CommonDto<InvestorInvestInfoDto> getInvestorInvestInfo(Integer investorId);
}
