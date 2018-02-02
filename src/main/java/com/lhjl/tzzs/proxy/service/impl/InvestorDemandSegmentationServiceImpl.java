package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorDemandSegmentationMapper;
import com.lhjl.tzzs.proxy.model.InvestorDemandSegmentation;
import com.lhjl.tzzs.proxy.service.InvestorDemandSegmentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
@Service
public class InvestorDemandSegmentationServiceImpl implements InvestorDemandSegmentationService {

    @Autowired
    private InvestorDemandSegmentationMapper investorDemandSegmentationMapper;

    @Override
    public Integer save(InvestorDemandSegmentation investorDemandSegmentation) {
       return investorDemandSegmentationMapper.insert(investorDemandSegmentation);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorDemandSegmentation investorDemandSegmentation = new InvestorDemandSegmentation();
        investorDemandSegmentation.setInvestorDemandId(investorId);
        investorDemandSegmentationMapper.delete(investorDemandSegmentation);
    }

    @Override
    public void delete(InvestorDemandSegmentation investorDemandSegmentation) {
        investorDemandSegmentationMapper.delete(investorDemandSegmentation);
    }

    @Override
    public Integer insertList(List<InvestorDemandSegmentation> investorDemandSegmentationList) {
        return investorDemandSegmentationMapper.insertList(investorDemandSegmentationList);
    }

    @Override
    public List<InvestorDemandSegmentation> select(InvestorDemandSegmentation investorDemandSegmentation) {
        List<InvestorDemandSegmentation> investorDemandSegmentationList = new ArrayList<>();
        investorDemandSegmentationList = investorDemandSegmentationMapper.select(investorDemandSegmentation);
        return investorDemandSegmentationList;
    }
}
