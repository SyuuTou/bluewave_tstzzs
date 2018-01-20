package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestorDemandSegmentation;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
public interface InvestorDemandSegmentationService {
    int save(InvestorDemandSegmentation investorDemandSegmentation);

    void deleteAll(Integer investorId);

    void delete(InvestorDemandSegmentation investorDemandSegmentation);

    int insertList(List<InvestorDemandSegmentation> investorDemandSegmentationList);

    List<InvestorDemandSegmentation> select(InvestorDemandSegmentation investorDemandSegmentation);
}
