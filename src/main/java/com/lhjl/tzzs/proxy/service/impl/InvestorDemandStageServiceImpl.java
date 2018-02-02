package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorDemandStageMapper;
import com.lhjl.tzzs.proxy.model.InvestorDemandCharacter;
import com.lhjl.tzzs.proxy.model.InvestorDemandStage;
import com.lhjl.tzzs.proxy.service.InvestorDemandStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
@Service
public class InvestorDemandStageServiceImpl implements InvestorDemandStageService {

    @Autowired
    private InvestorDemandStageMapper investorDemandStageMapper;

    @Override
    public Integer save(InvestorDemandStage investorDemandStage) {
       return  investorDemandStageMapper.insert(investorDemandStage);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorDemandStage investorDemandStage = new InvestorDemandStage();
        investorDemandStage.setInvestorDemandId(investorId);
        investorDemandStageMapper.delete(investorDemandStage);
    }

    @Override
    public void delete(InvestorDemandStage investorDemandStage) {
        investorDemandStageMapper.delete(investorDemandStage);
    }

    @Override
    public Integer insertList(List<InvestorDemandStage> investorDemandStageList) {
        return investorDemandStageMapper.insertList(investorDemandStageList);
    }

    @Override
    public List<InvestorDemandStage> select(InvestorDemandStage investorDemandStage) {
        List<InvestorDemandStage> investorDemandStageArrayList = new ArrayList<>();
        investorDemandStageArrayList = investorDemandStageMapper.select(investorDemandStage);
        return investorDemandStageArrayList;
    }
}
