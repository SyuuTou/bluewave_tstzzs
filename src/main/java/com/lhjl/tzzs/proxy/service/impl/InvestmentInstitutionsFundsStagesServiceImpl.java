package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsFundsStagesMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFunds;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFundsStages;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsFundsStagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class InvestmentInstitutionsFundsStagesServiceImpl implements InvestmentInstitutionsFundsStagesService{
    @Autowired
    private InvestmentInstitutionsFundsStagesMapper investmentInstitutionsFundsStagesMapper;

    @Override
    public void save(InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages) {
        investmentInstitutionsFundsStagesMapper.insert(investmentInstitutionsFundsStages);
    }

    @Override
    public void deleteAll(Integer fundId) {
        InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages = new InvestmentInstitutionsFundsStages();
        investmentInstitutionsFundsStages.setInvestmentInstitutionsFundsId(fundId);
        investmentInstitutionsFundsStagesMapper.delete(investmentInstitutionsFundsStages);
    }

    @Override
    public int insertList(List<InvestmentInstitutionsFundsStages> investmentInstitutionsFundsStagesList) {
        return investmentInstitutionsFundsStagesMapper.insertList(investmentInstitutionsFundsStagesList);
    }

    @Override
    public List<InvestmentInstitutionsFundsStages> select(InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages) {
       List<InvestmentInstitutionsFundsStages> investmentInstitutionsFundsStagesList = new ArrayList<>();
        investmentInstitutionsFundsStagesList = investmentInstitutionsFundsStagesMapper.select(investmentInstitutionsFundsStages);
        return investmentInstitutionsFundsStagesList;
    }
}
