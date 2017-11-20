package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionComplexOutputDto;

import java.util.Map;

public interface InvestmentInstitutionsService {
    /**
     * 根据机构id获取机构信息的接口
     * @param body
     * @return
     */
    CommonDto<InvestmentInstitutionComplexOutputDto> getInvestmentInstitutionsComlexInfo(Map<String,Integer> body);
}
