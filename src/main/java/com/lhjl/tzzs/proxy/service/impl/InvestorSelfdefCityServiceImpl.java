package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorSelfdefCityMapper;
import com.lhjl.tzzs.proxy.model.InvestorSelfdefCity;
import com.lhjl.tzzs.proxy.service.InvestorSelfdefCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class InvestorSelfdefCityServiceImpl implements InvestorSelfdefCityService {

    @Autowired
    private InvestorSelfdefCityMapper investorSelfdefCityMapper;

    @Override
    public Integer save(InvestorSelfdefCity investorSelfdefCity) {
        return investorSelfdefCityMapper.insert(investorSelfdefCity);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorSelfdefCity investorSelfdefCity = new InvestorSelfdefCity();
        investorSelfdefCity.setId(investorId);
        investorSelfdefCityMapper.delete(investorSelfdefCity);
    }

    @Override
    public void delete(InvestorSelfdefCity investorSelfdefCity) {
        investorSelfdefCityMapper.delete(investorSelfdefCity);
    }

    @Override
    public Integer insertList(List<InvestorSelfdefCity> investorSelfdefCityList) {
        return investorSelfdefCityMapper.insertList(investorSelfdefCityList);
    }

    @Override
    public List<InvestorSelfdefCity> select(InvestorSelfdefCity investorSelfdefCity) {
        return investorSelfdefCityMapper.select(investorSelfdefCity);
    }
}
