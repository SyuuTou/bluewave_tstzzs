package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionPreferStageMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionPreferStage;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionPreferStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-24 14:08:57
 */
@Service
public class InvestmentInstitutionPreferStageServiceImpl implements InvestmentInstitutionPreferStageService {

    @Autowired
    private InvestmentInstitutionPreferStageMapper investmentInstitutionPreferStageMapper;

    @Override
    public int save(InvestmentInstitutionPreferStage investmentInstitutionPreferStage) {
        return investmentInstitutionPreferStageMapper.insert(investmentInstitutionPreferStage);
    }

    @Override
    public void deleteAll(Integer companyId) {
        InvestmentInstitutionPreferStage investmentInstitutionPreferStage = new InvestmentInstitutionPreferStage();
        investmentInstitutionPreferStage.setCompanyId(companyId);
        investmentInstitutionPreferStageMapper.delete(investmentInstitutionPreferStage);
    }

    @Override
    public void delete(InvestmentInstitutionPreferStage investmentInstitutionPreferStage) {
        investmentInstitutionPreferStageMapper.delete(investmentInstitutionPreferStage);
    }

    @Override
    public int insertList(List<InvestmentInstitutionPreferStage> investmentInstitutionPreferStageList) {
        return investmentInstitutionPreferStageMapper.insertList(investmentInstitutionPreferStageList);
    }

    @Override
    public List<InvestmentInstitutionPreferStage> select(InvestmentInstitutionPreferStage investmentInstitutionPreferStage) {
        List<InvestmentInstitutionPreferStage> investmentInstitutionPreferStageList = new ArrayList<>();
        investmentInstitutionPreferStageList = investmentInstitutionPreferStageMapper.select(investmentInstitutionPreferStage);
        return investmentInstitutionPreferStageList;
    }
}
