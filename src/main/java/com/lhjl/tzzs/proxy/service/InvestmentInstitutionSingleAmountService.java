package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionPreferStage;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionSingleAmount;

import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-24 16:19:04
 */
public interface InvestmentInstitutionSingleAmountService {
    int save(InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount);

    void deleteAll(Integer projectId);

    void delete(InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount);

    int insertList(List<InvestmentInstitutionSingleAmount> investmentInstitutionSingleAmountList);

    List<InvestmentInstitutionSingleAmount> select(InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount);
}
