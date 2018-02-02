package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorWorkExperienceMapper;
import com.lhjl.tzzs.proxy.model.InvestorWorkExperience;
import com.lhjl.tzzs.proxy.service.InvestorWorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class InvestorWorkExperienceServiceImpl implements InvestorWorkExperienceService {

    @Autowired
    private InvestorWorkExperienceMapper investorWorkExperienceMapper;

    @Override
    public Integer save(InvestorWorkExperience investorWorkExperience) {
        return investorWorkExperienceMapper.insert(investorWorkExperience);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorWorkExperience investorWorkExperience = new InvestorWorkExperience();
        investorWorkExperience.setId(investorId);
        investorWorkExperienceMapper.delete(investorWorkExperience);
    }

    @Override
    public void delete(InvestorWorkExperience investorWorkExperience) {
        investorWorkExperienceMapper.delete(investorWorkExperience);
    }

    @Override
    public Integer insertList(List<InvestorWorkExperience> investorWorkExperienceList) {
        return investorWorkExperienceMapper.insertList(investorWorkExperienceList);
    }

    @Override
    public List<InvestorWorkExperience> select(InvestorWorkExperience investorWorkExperience) {
        return investorWorkExperienceMapper.select(investorWorkExperience);
    }
}
