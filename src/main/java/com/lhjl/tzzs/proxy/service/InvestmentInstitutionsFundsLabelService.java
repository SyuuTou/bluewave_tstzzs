package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFundsLabel;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface InvestmentInstitutionsFundsLabelService {

    void save(InvestmentInstitutionsFundsLabel investmentInstitutionsFundsLabel);

    void deleteAll(Integer fundId);

    int insertList(List<InvestmentInstitutionsFundsLabel> investmentInstitutionsFundsLabelList);
}
