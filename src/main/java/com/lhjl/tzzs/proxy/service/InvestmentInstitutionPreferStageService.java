package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionPreferSegmentation;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionPreferStage;

import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-24 16:18:48
 */
public interface InvestmentInstitutionPreferStageService {
    int save(InvestmentInstitutionPreferStage investmentInstitutionPreferStage);

    void deleteAll(Integer projectId);

    void delete(InvestmentInstitutionPreferStage investmentInstitutionPreferStage);

    int insertList(List<InvestmentInstitutionPreferStage> investmentInstitutionPreferStageList);

    List<InvestmentInstitutionPreferStage> select(InvestmentInstitutionPreferStage investmentInstitutionPreferStage);
}
