package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorBusinessMapper;
import com.lhjl.tzzs.proxy.model.InvestorBusiness;
import com.lhjl.tzzs.proxy.service.InvestorBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class InvestorBusinessServiceImpl implements InvestorBusinessService {

    @Autowired
    private InvestorBusinessMapper investorBusinessMapper;

    @Override
    public Integer save(InvestorBusiness investorBusiness) {
        return investorBusinessMapper.insert(investorBusiness);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorBusiness investorBusiness = new InvestorBusiness();
        investorBusiness.setId(investorId);
        investorBusinessMapper.delete(investorBusiness);
    }

    @Override
    public void delete(InvestorBusiness investorBusiness) {
        investorBusinessMapper.delete(investorBusiness);
    }

    @Override
    public Integer insertList(List<InvestorBusiness> investorBusinessList) {
        return investorBusinessMapper.insertList(investorBusinessList);
    }

    @Override
    public List<InvestorBusiness> select(InvestorBusiness investorBusiness) {
        return investorBusinessMapper.select(investorBusiness);
    }
}
