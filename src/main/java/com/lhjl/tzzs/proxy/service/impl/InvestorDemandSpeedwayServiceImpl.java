package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorDemandSpeedwayMapper;
import com.lhjl.tzzs.proxy.model.InvestorDemandSpeedway;
import com.lhjl.tzzs.proxy.service.InvestorDemandSpeedwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
@Service
public class InvestorDemandSpeedwayServiceImpl implements InvestorDemandSpeedwayService {

    @Autowired
    private InvestorDemandSpeedwayMapper investorDemandSpeedwayMapper;

    @Override
    public Integer save(InvestorDemandSpeedway investorDemandSpeedway) {
        return investorDemandSpeedwayMapper.insert(investorDemandSpeedway);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorDemandSpeedway investorDemandSpeedway = new InvestorDemandSpeedway();
        investorDemandSpeedway.setInvestorDemandId(investorId);
        investorDemandSpeedwayMapper.delete(investorDemandSpeedway);
    }

    @Override
    public void delete(InvestorDemandSpeedway investorDemandSpeedway) {
        investorDemandSpeedwayMapper.delete(investorDemandSpeedway);
    }

    @Override
    public Integer insertList(List<InvestorDemandSpeedway> investorDemandSpeedwayList) {

        return investorDemandSpeedwayMapper.insertList(investorDemandSpeedwayList);
    }

    @Override
    public List<InvestorDemandSpeedway> select(InvestorDemandSpeedway investorDemandSpeedway) {
        List<InvestorDemandSpeedway> investorDemandStageArrayList = new ArrayList<>();
        investorDemandStageArrayList = investorDemandSpeedwayMapper.select(investorDemandSpeedway);
        return investorDemandStageArrayList;
    }
}
