package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestorDemandArea;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/31.
 */
public interface InvestorDemandAreaService {
    Integer save(InvestorDemandArea investorDemandArea);

    void deleteAll(Integer investorDemandId);

    void delete(InvestorDemandArea investorDemandArea);

    Integer insertList(List<InvestorDemandArea> investorDemandAreaList);

    List<InvestorDemandArea> select(InvestorDemandArea investorDemandArea);
}
