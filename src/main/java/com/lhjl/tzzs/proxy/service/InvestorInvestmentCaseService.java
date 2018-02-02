package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestorInvestmentCase;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/31.
 */
public interface InvestorInvestmentCaseService {

    Integer save(InvestorInvestmentCase investorInvestmentCase);

    void deleteAll(Integer investorId);

    void delete(InvestorInvestmentCase investorInvestmentCase);

    Integer insertList(List<InvestorInvestmentCase> investorInvestmentCaseList);

    List<InvestorInvestmentCase> select(InvestorInvestmentCase investorInvestmentCase);
}
