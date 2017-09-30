package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;

import java.util.List;
import java.util.Map;


public interface InvestmentBackstageService {
    CommonDto<String> adminAddInvestmentBackstage(InvestmentInstitutions body);

    CommonDto<List<Map<String, Object>>>  findAllInvestment();
}
