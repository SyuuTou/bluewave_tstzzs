package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsFundsLabelMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectTeamMemberSelfcityMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFunds;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFundsLabel;
import com.lhjl.tzzs.proxy.model.ProjectTeamMemberSelfcity;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsFundsLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class InvestmentInstitutionsFundsLabelServiceImpl implements InvestmentInstitutionsFundsLabelService{

    @Autowired
    private InvestmentInstitutionsFundsLabelMapper investmentInstitutionsFundsLabelMapper;

    @Override
    public void save(InvestmentInstitutionsFundsLabel investmentInstitutionsFundsLabel) {
        investmentInstitutionsFundsLabelMapper.insert(investmentInstitutionsFundsLabel);
    }

    @Override
    public void deleteAll(Integer fundId) {
        InvestmentInstitutionsFundsLabel investmentInstitutionsFundsLabel = new InvestmentInstitutionsFundsLabel();
        investmentInstitutionsFundsLabel.setInvestmentInstitutionsFundsId(fundId);
        investmentInstitutionsFundsLabelMapper.delete(investmentInstitutionsFundsLabel);
    }

    @Override
    public int insertList(List<InvestmentInstitutionsFundsLabel> investmentInstitutionsFundsLabelList) {
        return investmentInstitutionsFundsLabelMapper.insertList(investmentInstitutionsFundsLabelList);
    }
}
