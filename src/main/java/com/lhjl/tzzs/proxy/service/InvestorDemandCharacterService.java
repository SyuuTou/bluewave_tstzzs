package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestorDemandCharacter;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
public interface InvestorDemandCharacterService {
    int save(InvestorDemandCharacter investorDemandCharacter);

    void deleteAll(Integer investorId);

    void delete(InvestorDemandCharacter investorDemandCharacter);

    int insertList(List<InvestorDemandCharacter> investorDemandCharacterList);

    List<InvestorDemandCharacter> select(InvestorDemandCharacter investorDemandCharacter);

}
