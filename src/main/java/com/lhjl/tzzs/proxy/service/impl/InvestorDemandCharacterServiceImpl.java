package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorDemandCharacterMapper;
import com.lhjl.tzzs.proxy.model.InvestorDemandCharacter;
import com.lhjl.tzzs.proxy.service.InvestorDemandCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
@Service
public class InvestorDemandCharacterServiceImpl implements InvestorDemandCharacterService {

    @Autowired
    private InvestorDemandCharacterMapper investorDemandCharacterMapper;

    @Override
    public Integer save(InvestorDemandCharacter investorDemandCharacter) {
        return  investorDemandCharacterMapper.insert(investorDemandCharacter);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorDemandCharacter investorDemandCharacter = new InvestorDemandCharacter();
        investorDemandCharacter.setInvestorDemandId(investorId);
        investorDemandCharacterMapper.delete(investorDemandCharacter);
    }

    @Override
    public void delete(InvestorDemandCharacter investorDemandCharacter) {
        investorDemandCharacterMapper.delete(investorDemandCharacter);
    }

    @Override
    public Integer insertList(List<InvestorDemandCharacter> investorDemandCharacterList) {
        return  investorDemandCharacterMapper.insertList(investorDemandCharacterList);
    }

    @Override
    public List<InvestorDemandCharacter> select(InvestorDemandCharacter investorDemandCharacter) {
        List<InvestorDemandCharacter> investorDemandCharacterList = new ArrayList<>();
        investorDemandCharacterList = investorDemandCharacterMapper.select(investorDemandCharacter);
        return investorDemandCharacterList;
    }
}
