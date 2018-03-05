package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.InvestorSegmentation;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public interface InvestorSegmentationService {

    Integer save(InvestorSegmentation investorSegmentation);

    void deleteAll(Integer investorId);

    void delete(InvestorSegmentation investorSegmentation);

    Integer insertList(List<InvestorSegmentation> investorSegmentationList);

    List<InvestorSegmentation> select(InvestorSegmentation investorSegmentation);

	Integer edit(InvestorSegmentation body);
    
}
