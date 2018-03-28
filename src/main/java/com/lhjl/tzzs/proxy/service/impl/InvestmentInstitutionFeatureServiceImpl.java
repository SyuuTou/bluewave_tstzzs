package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionFeatureMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionFeature;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author caochuangui
 * @date 2018-1-24 14:08:57
 */
@Service
public class InvestmentInstitutionFeatureServiceImpl implements InvestmentInstitutionFeatureService {

    @Autowired
    private InvestmentInstitutionFeatureMapper investmentInstitutionFeatureMapper;

    @Override
    public Integer save(InvestmentInstitutionFeature investmentInstitutionFeature) {
        return investmentInstitutionFeatureMapper.insertSelective(investmentInstitutionFeature);
    }

    @Override
    public InvestmentInstitutionFeature selectByPrimaryKey(Integer companyId) {
        return investmentInstitutionFeatureMapper.selectByPrimaryKey(companyId);
    }

    @Override
    public Integer updateByPrimaryKey(InvestmentInstitutionFeature investmentInstitutionFeature) {
        return investmentInstitutionFeatureMapper.updateByPrimaryKeySelective(investmentInstitutionFeature);
    }
}
