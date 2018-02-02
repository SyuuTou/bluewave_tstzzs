package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestorSelfdefCity;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public interface InvestorSelfdefCityService {

    Integer save(InvestorSelfdefCity investorSelfdefCity);

    void deleteAll(Integer investorId);

    void delete(InvestorSelfdefCity investorSelfdefCity);

    Integer insertList(List<InvestorSelfdefCity> investorSelfdefCityList);

    List<InvestorSelfdefCity> select(InvestorSelfdefCity investorSelfdefCity);
}
