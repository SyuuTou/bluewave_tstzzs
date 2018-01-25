package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionInvestTypeMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionInvestType;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionInvestTypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/24.
 */
@Service
public class InvestmentInstitutionInvestTypeServiceImpl implements InvestmentInstitutionInvestTypeService{

    @Autowired
    private InvestmentInstitutionInvestTypeMapper investmentInstitutionInvestTypeMapper;

    @Override
    public int save(InvestmentInstitutionInvestType investmentInstitutionInvestType) {
        return investmentInstitutionInvestTypeMapper.insert(investmentInstitutionInvestType);
    }

    @Override
    public void deleteAll(Integer companyId) {
        InvestmentInstitutionInvestType investmentInstitutionInvestType = new InvestmentInstitutionInvestType();
        investmentInstitutionInvestType.setCompanyId(companyId);
        investmentInstitutionInvestTypeMapper.delete(investmentInstitutionInvestType);
    }

    @Override
    public void delete(InvestmentInstitutionInvestType investmentInstitutionInvestType) {
        investmentInstitutionInvestTypeMapper.delete(investmentInstitutionInvestType);
    }

    @Override
    public int insertList(List<InvestmentInstitutionInvestType> investmentInstitutionInvestTypeList) {
        return investmentInstitutionInvestTypeMapper.insertList(investmentInstitutionInvestTypeList);
    }

    @Override
    public List<InvestmentInstitutionInvestType> select(InvestmentInstitutionInvestType investmentInstitutionInvestType) {
        return investmentInstitutionInvestTypeMapper.select(investmentInstitutionInvestType);
    }
}
