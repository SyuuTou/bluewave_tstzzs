package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestorBusiness;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public interface InvestorBusinessService {
    Integer save(InvestorBusiness investorBusiness);

    void deleteAll(Integer investorId);

    void delete(InvestorBusiness investorBusiness);

    Integer insertList(List<InvestorBusiness> investorBusinessList);

    List<InvestorBusiness> select(InvestorBusiness investorBusiness);
}
