package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorCertificationDto;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public interface InvestorCertificationInfoService {
    CommonDto<String> addOrUpdateInvestorCertification(InvestorCertificationDto body);

    CommonDto<InvestorCertificationDto> getInvestorCertification(Integer investorId);
}
