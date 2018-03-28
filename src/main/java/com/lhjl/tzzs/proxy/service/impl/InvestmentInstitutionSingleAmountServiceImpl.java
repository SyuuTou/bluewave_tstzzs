package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionSingleAmountMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionSingleAmount;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionSingleAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-24 14:08:57
 */
@Service
public class InvestmentInstitutionSingleAmountServiceImpl implements InvestmentInstitutionSingleAmountService {

    @Autowired
    private InvestmentInstitutionSingleAmountMapper investmentInstitutionSingleAmountMapper;

    @Override
    public int save(InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount) {
        return investmentInstitutionSingleAmountMapper.insertSelective(investmentInstitutionSingleAmount);
    }

    @Override
    public void deleteAll(Integer companyId) {
        InvestmentInstitutionSingleAmount investmentInstitutionsFundsSegmentation = new InvestmentInstitutionSingleAmount();
        investmentInstitutionsFundsSegmentation.setCompanyId(companyId);
        investmentInstitutionSingleAmountMapper.delete(investmentInstitutionsFundsSegmentation);
    }

    @Override
    public int insertList(List<InvestmentInstitutionSingleAmount> investmentInstitutionSingleAmount) {
        return investmentInstitutionSingleAmountMapper.insertList(investmentInstitutionSingleAmount);
    }

    @Override
    public List<InvestmentInstitutionSingleAmount> select(InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount) {
        List<InvestmentInstitutionSingleAmount> investmentInstitutionSingleAmountList = new ArrayList<>();
        investmentInstitutionSingleAmountList = investmentInstitutionSingleAmountMapper.select(investmentInstitutionSingleAmount);
        return investmentInstitutionSingleAmountList;
    }

    @Override
    public void delete(InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount) {
        investmentInstitutionSingleAmountMapper.delete(investmentInstitutionSingleAmount);
    }
}
