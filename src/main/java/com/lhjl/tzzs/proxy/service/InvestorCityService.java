package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestorCity;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public interface InvestorCityService {
    Integer save(InvestorCity investorCity);

    void deleteAll(Integer investorId);

    void delete(InvestorCity investorCity);

    Integer insertList(List<InvestorCity> investorSegmentationList);

    List<InvestorCity> select(InvestorCity investorCity);
}
