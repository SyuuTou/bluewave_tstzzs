package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionFundManageMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionFundManage;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionFundManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author caochuangui
 * @date 2018-1-24 16:17:00
 */
@Service
public class InvestmentInstitutionFundManageServiceImpl implements InvestmentInstitutionFundManageService{

    @Autowired
    private InvestmentInstitutionFundManageMapper investmentInstitutionFundManageMapper;


    @Override
    public InvestmentInstitutionFundManage selectByPrimaryKey(Integer companyId) {
        return investmentInstitutionFundManageMapper.selectByPrimaryKey(companyId);
    }

    /*@Override
    public Integer updateByPrimaryKey(InvestmentInstitutionFundManage investmentInstitutionFundManage) {
        return investmentInstitutionFundManageMapper.updateByPrimaryKeySelective(investmentInstitutionFundManage);
    }*/
}
