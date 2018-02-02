package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestorWorkExperience;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public interface InvestorWorkExperienceService {
    Integer save(InvestorWorkExperience investorWorkExperience);

    void deleteAll(Integer investorId);

    void delete(InvestorWorkExperience investorWorkExperience);

    Integer insertList(List<InvestorWorkExperience> investorWorkExperienceList);

    List<InvestorWorkExperience> select(InvestorWorkExperience investorWorkExperience);
}
