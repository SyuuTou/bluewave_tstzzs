package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionPreferSegmentation;

import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-24 16:18:33
 */
public interface InvestmentInstitutionPreferSegmentationService {
    int save(InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation);

    void deleteAll(Integer projectId);

    void delete(InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation);

    int insertList(List<InvestmentInstitutionPreferSegmentation> investmentInstitutionPreferSegmentationList);

    List<InvestmentInstitutionPreferSegmentation> select(InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation);
}
