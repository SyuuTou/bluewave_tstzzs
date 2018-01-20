package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsFundsSegmentationMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFunds;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFundsSegmentation;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFundsStages;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsFundsSegmentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class InvestmentInstitutionsFundsSegmentationServiceImpl implements InvestmentInstitutionsFundsSegmentationService{
    @Autowired
    private InvestmentInstitutionsFundsSegmentationMapper investmentInstitutionsFundsSegmentationMapper;

    @Override
    public void save(InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation) {
        investmentInstitutionsFundsSegmentationMapper.insert(investmentInstitutionsFundsSegmentation);
    }

    @Override
    public void deleteAll(Integer fundId) {
        InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation = new InvestmentInstitutionsFundsSegmentation();
        investmentInstitutionsFundsSegmentation.setInvestmentInstitutionsFundsId(fundId);
        investmentInstitutionsFundsSegmentationMapper.delete(investmentInstitutionsFundsSegmentation);
    }

    @Override
    public int insertList(List<InvestmentInstitutionsFundsSegmentation> investmentInstitutionsFundsLabelList) {
        return investmentInstitutionsFundsSegmentationMapper.insertList(investmentInstitutionsFundsLabelList);
    }

    @Override
    public List<InvestmentInstitutionsFundsSegmentation> select(InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation) {
        List<InvestmentInstitutionsFundsSegmentation> investmentInstitutionsFundsSegmentationList = new ArrayList<>();
        investmentInstitutionsFundsSegmentationList = investmentInstitutionsFundsSegmentationMapper.select(investmentInstitutionsFundsSegmentation);
        return investmentInstitutionsFundsSegmentationList;
    }
}
