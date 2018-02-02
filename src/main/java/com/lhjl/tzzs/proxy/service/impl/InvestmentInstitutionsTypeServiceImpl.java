package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsTypeMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsType;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/24.
 */
@Service
public class InvestmentInstitutionsTypeServiceImpl implements InvestmentInstitutionsTypeService {

    @Autowired
    private InvestmentInstitutionsTypeMapper investmentInstitutionsTypeMapper;

    @Override
    public int save(InvestmentInstitutionsType investmentInstitutionsType) {
        return investmentInstitutionsTypeMapper.insert(investmentInstitutionsType);
    }

    @Override
    public void deleteAll(Integer companyId) {
        InvestmentInstitutionsType investmentInstitutionsType = new InvestmentInstitutionsType();
        investmentInstitutionsType.setInvestmentInstitutionsId(companyId);
        investmentInstitutionsTypeMapper.delete(investmentInstitutionsType);
    }

    @Override
    public void delete(InvestmentInstitutionsType investmentInstitutionsType) {
        investmentInstitutionsTypeMapper.delete(investmentInstitutionsType);
    }

    @Override
    public int insertList(List<InvestmentInstitutionsType> investmentInstitutionsTypeList) {
        return investmentInstitutionsTypeMapper.insertList(investmentInstitutionsTypeList);
    }

    @Override
    public List<InvestmentInstitutionsType> select(InvestmentInstitutionsType investmentInstitutionsType) {
        return investmentInstitutionsTypeMapper.select(investmentInstitutionsType);
    }
}
