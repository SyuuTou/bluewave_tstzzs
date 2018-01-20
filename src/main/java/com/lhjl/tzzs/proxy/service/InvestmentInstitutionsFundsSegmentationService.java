package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFundsLabel;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFundsSegmentation;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface InvestmentInstitutionsFundsSegmentationService {
    void save(InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation);

    void deleteAll(Integer fundId);

    List<InvestmentInstitutionsFundsSegmentation> select(InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation);

    int insertList(List<InvestmentInstitutionsFundsSegmentation> investmentInstitutionsFundsSegmentationList);

}
