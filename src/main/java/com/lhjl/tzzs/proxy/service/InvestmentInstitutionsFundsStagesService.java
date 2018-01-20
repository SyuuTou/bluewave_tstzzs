package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFundsSegmentation;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFundsStages;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface InvestmentInstitutionsFundsStagesService {
    void save(InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages);

    List<InvestmentInstitutionsFundsStages> select(InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages);

    void deleteAll(Integer fundId);

    int insertList(List<InvestmentInstitutionsFundsStages> investmentInstitutionsFundsStagesList);

}
