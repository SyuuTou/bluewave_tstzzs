package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionClassicCase;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionInvestType;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsType;

import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-24 16:18:17
 */
public interface InvestmentInstitutionsTypeService {
    int save(InvestmentInstitutionsType investmentInstitutionsType);

    void deleteAll(Integer companyId);

    void delete(InvestmentInstitutionsType investmentInstitutionsType);

    int insertList(List<InvestmentInstitutionsType> investmentInstitutionsTypeList);

    List<InvestmentInstitutionsType> select(InvestmentInstitutionsType investmentInstitutionsType);

}
