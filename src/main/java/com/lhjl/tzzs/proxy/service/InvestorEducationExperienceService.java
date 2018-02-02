package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestorEducationExperience;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public interface InvestorEducationExperienceService {

    Integer save(InvestorEducationExperience investorEducationExperience);

    void deleteAll(Integer investorId);

    void delete(InvestorEducationExperience investorEducationExperience);

    Integer insertList(List<InvestorEducationExperience> investorEducationExperienceList);

    List<InvestorEducationExperience> select(InvestorEducationExperience investorEducationExperience);
}
