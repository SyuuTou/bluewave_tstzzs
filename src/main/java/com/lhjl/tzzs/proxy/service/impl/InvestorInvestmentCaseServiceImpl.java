package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorInvestmentCaseMapper;
import com.lhjl.tzzs.proxy.model.InvestorInvestmentCase;
import com.lhjl.tzzs.proxy.service.InvestorInvestmentCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/31.
 */
@Service
public class InvestorInvestmentCaseServiceImpl implements InvestorInvestmentCaseService {

    @Autowired
    private InvestorInvestmentCaseMapper investorInvestmentCaseMapper;

    @Override
    public Integer save(InvestorInvestmentCase investorInvestmentCase) {
        return  investorInvestmentCaseMapper.insert(investorInvestmentCase);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorInvestmentCase investorInvestmentCase = new InvestorInvestmentCase();
        investorInvestmentCase.setInvestorId(investorId);
        investorInvestmentCaseMapper.delete(investorInvestmentCase);
    }

    @Override
    public void delete(InvestorInvestmentCase investorInvestmentCase) {
        investorInvestmentCaseMapper.delete(investorInvestmentCase);
    }

    @Override
    public Integer insertList(List<InvestorInvestmentCase> investorInvestmentCaseList) {
        return  investorInvestmentCaseMapper.insertList(investorInvestmentCaseList);
    }

    @Override
    public List<InvestorInvestmentCase> select(InvestorInvestmentCase investorInvestmentCase) {
        List<InvestorInvestmentCase> investorInvestmentCaseList = new ArrayList<>();
        investorInvestmentCaseList = investorInvestmentCaseMapper.select(investorInvestmentCase);
        return investorInvestmentCaseList;
    }
}
