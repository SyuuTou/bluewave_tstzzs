package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionFeature;

/**
 * @author caochuangui
 * @date 2018-1-24 16:17:46
 */
public interface InvestmentInstitutionFeatureService {

    Integer save(InvestmentInstitutionFeature investmentInstitutionFeature);

    InvestmentInstitutionFeature selectByPrimaryKey(Integer companyId);

    Integer updateByPrimaryKey(InvestmentInstitutionFeature investmentInstitutionFeature);

}
