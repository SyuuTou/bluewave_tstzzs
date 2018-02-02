package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestorDemandCharacter;
import com.lhjl.tzzs.proxy.model.InvestorDemandStage;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
public interface InvestorDemandStageService {

    Integer save(InvestorDemandStage investorDemandStage);

    void deleteAll(Integer investorId);

    void delete(InvestorDemandStage investorDemandStage);

    Integer insertList(List<InvestorDemandStage> investorDemandStageList);

    List<InvestorDemandStage> select(InvestorDemandStage investorDemandStage);
}
