package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorCityMapper;
import com.lhjl.tzzs.proxy.model.InvestorCity;
import com.lhjl.tzzs.proxy.service.InvestorCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class InvestorCityServiceImpl implements InvestorCityService {
    @Autowired
    private InvestorCityMapper investorCityMapper;

    @Override
    public Integer save(InvestorCity investorCity) {
        return investorCityMapper.insert(investorCity);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorCity investorCity = new InvestorCity();
        investorCity.setId(investorId);
        investorCityMapper.delete(investorCity);
    }

    @Override
    public void delete(InvestorCity investorCity) {
        investorCityMapper.delete(investorCity);
    }

    @Override
    public Integer insertList(List<InvestorCity> investorSegmentationList) {
        return investorCityMapper.insertList(investorSegmentationList);
    }

    @Override
    public List<InvestorCity> select(InvestorCity investorCity) {
        return investorCityMapper.select(investorCity);
    }
}
