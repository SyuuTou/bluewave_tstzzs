package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionClassicCase;

import java.util.List;

/**
 * @author caochuangui
 * @date2018-1-24 16:17:25
 */
public interface InvestmentInstitutionClassicCaseService {
    int save(InvestmentInstitutionClassicCase investmentInstitutionClassicCase);

    void deleteAll(Integer companyId);

    void delete(InvestmentInstitutionClassicCase investmentInstitutionClassicCase);

    int insertList(List<InvestmentInstitutionClassicCase> investmentInstitutionClassicCaseList);

    List<InvestmentInstitutionClassicCase> select(InvestmentInstitutionClassicCase investmentInstitutionClassicCase);
}
