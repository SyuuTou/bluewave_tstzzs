package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorEducationExperienceMapper;
import com.lhjl.tzzs.proxy.model.InvestorEducationExperience;
import com.lhjl.tzzs.proxy.service.InvestorEducationExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class InvestorEducationExperienceServiceImpl implements InvestorEducationExperienceService {

    @Autowired
    private InvestorEducationExperienceMapper investorEducationExperienceMapper;

    @Override
    public Integer save(InvestorEducationExperience investorEducationExperience) {
        return investorEducationExperienceMapper.insert(investorEducationExperience);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorEducationExperience investorEducationExperience = new InvestorEducationExperience();
        investorEducationExperience.setId(investorId);
        investorEducationExperienceMapper.delete(investorEducationExperience);
    }

    @Override
    public void delete(InvestorEducationExperience investorEducationExperience) {
        investorEducationExperienceMapper.delete(investorEducationExperience);
    }

    @Override
    public Integer insertList(List<InvestorEducationExperience> investorEducationExperienceList) {
        return investorEducationExperienceMapper.insertList(investorEducationExperienceList);
    }

    @Override
    public List<InvestorEducationExperience> select(InvestorEducationExperience investorEducationExperience) {
        return investorEducationExperienceMapper.select(investorEducationExperience);
    }
}
