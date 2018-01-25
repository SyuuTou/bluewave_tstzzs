package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionClassicCaseMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionClassicCase;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionClassicCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/24.
 */
@Service
public class InvestmentInstitutionClassicCaseServiceImpl implements InvestmentInstitutionClassicCaseService{

    @Autowired
    private InvestmentInstitutionClassicCaseMapper investmentInstitutionClassicCaseMapper;

    @Override
    public int save(InvestmentInstitutionClassicCase investmentInstitutionClassicCase) {
        return investmentInstitutionClassicCaseMapper.insert(investmentInstitutionClassicCase);
    }

    @Override
    public void deleteAll(Integer companyId) {
        InvestmentInstitutionClassicCase investmentInstitutionClassicCase = new InvestmentInstitutionClassicCase();
        investmentInstitutionClassicCase.setCompanyId(companyId);
        investmentInstitutionClassicCaseMapper.delete(investmentInstitutionClassicCase);
    }

    @Override
    public void delete(InvestmentInstitutionClassicCase investmentInstitutionClassicCase) {
        investmentInstitutionClassicCaseMapper.delete(investmentInstitutionClassicCase);
    }

    @Override
    public int insertList(List<InvestmentInstitutionClassicCase> investmentInstitutionClassicCaseList) {
        return investmentInstitutionClassicCaseMapper.insertList(investmentInstitutionClassicCaseList);
    }

    @Override
    public List<InvestmentInstitutionClassicCase> select(InvestmentInstitutionClassicCase investmentInstitutionClassicCase) {
        return investmentInstitutionClassicCaseMapper.select(investmentInstitutionClassicCase);
    }
}
