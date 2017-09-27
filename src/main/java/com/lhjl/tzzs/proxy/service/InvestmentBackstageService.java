package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;


public interface InvestmentBackstageService {
    CommonDto<String> adminAddInvestmentBackstage(InvestmentInstitutions body);
}
