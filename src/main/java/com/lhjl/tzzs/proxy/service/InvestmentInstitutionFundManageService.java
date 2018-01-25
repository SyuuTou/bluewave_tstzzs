package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionFundManage;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionInvestType;

/**
 * @author caochuangui
 * @date 2018-1-24 16:17:59
 */
public interface InvestmentInstitutionFundManageService {
    Integer save(InvestmentInstitutionFundManage investmentInstitutionFundManage);

    InvestmentInstitutionFundManage selectByPrimaryKey(Integer companyId);

    Integer updateByPrimaryKey(InvestmentInstitutionFundManage investmentInstitutionFundManage);
}
