package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorDemandAreaMapper;
import com.lhjl.tzzs.proxy.model.InvestorDemandArea;
import com.lhjl.tzzs.proxy.service.InvestorDemandAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/31.
 */
@Service
public class InvestorDemandAreaServiceImpl implements InvestorDemandAreaService {

    @Autowired
    private InvestorDemandAreaMapper investorDemandAreaMapper;

    @Override
    public Integer save(InvestorDemandArea investorDemandArea) {
        return  investorDemandAreaMapper.insert(investorDemandArea);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorDemandArea investorDemandArea = new InvestorDemandArea();
        investorDemandArea.setInvestorDemandId(investorId);
        investorDemandAreaMapper.delete(investorDemandArea);
    }

    @Override
    public void delete(InvestorDemandArea investorDemandArea) {
        investorDemandAreaMapper.delete(investorDemandArea);
    }

    @Override
    public Integer insertList(List<InvestorDemandArea> investorDemandAreaList) {
        return  investorDemandAreaMapper.insertList(investorDemandAreaList);
    }

    @Override
    public List<InvestorDemandArea> select(InvestorDemandArea investorDemandArea) {
        List<InvestorDemandArea> investorDemandAreaList = new ArrayList<>();
        investorDemandAreaList = investorDemandAreaMapper.select(investorDemandArea);
        return investorDemandAreaList;
    }
}
