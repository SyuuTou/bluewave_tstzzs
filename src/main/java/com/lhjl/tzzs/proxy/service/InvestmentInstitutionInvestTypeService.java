package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionClassicCase;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionInvestType;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;

import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-24 16:18:17
 */
public interface InvestmentInstitutionInvestTypeService {
    int save(InvestmentInstitutionInvestType investmentInstitutionInvestType);

    void deleteAll(Integer companyId);

    void delete(InvestmentInstitutionInvestType investmentInstitutionInvestType);

    int insertList(List<InvestmentInstitutionInvestType> investmentInstitutionInvestTypeList);

    List<InvestmentInstitutionInvestType> select(InvestmentInstitutionInvestType investmentInstitutionInvestType);

}
