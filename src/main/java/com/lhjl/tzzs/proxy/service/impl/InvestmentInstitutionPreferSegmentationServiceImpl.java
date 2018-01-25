package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionPreferSegmentationMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionPreferSegmentation;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionPreferSegmentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-24 14:08:57
 */
@Service
public class InvestmentInstitutionPreferSegmentationServiceImpl implements InvestmentInstitutionPreferSegmentationService {

    @Autowired
    private InvestmentInstitutionPreferSegmentationMapper investmentInstitutionPreferSegmentationMapper;

    @Override
    public void delete(InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation) {
        investmentInstitutionPreferSegmentationMapper.delete(investmentInstitutionPreferSegmentation);
    }
    @Override
    public int save(InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation) {
        return investmentInstitutionPreferSegmentationMapper.insert(investmentInstitutionPreferSegmentation);
    }

    @Override
    public void deleteAll(Integer companyId) {
        InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation = new InvestmentInstitutionPreferSegmentation();
        investmentInstitutionPreferSegmentation.setCompanyId(companyId);
        investmentInstitutionPreferSegmentationMapper.delete(investmentInstitutionPreferSegmentation);
    }

    @Override
    public int insertList(List<InvestmentInstitutionPreferSegmentation> investmentInstitutionPreferSegmentationList) {
        return investmentInstitutionPreferSegmentationMapper.insertList(investmentInstitutionPreferSegmentationList);
    }

    @Override
    public List<InvestmentInstitutionPreferSegmentation> select(InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation) {
        List<InvestmentInstitutionPreferSegmentation> investmentInstitutionsFundsSegmentationList = new ArrayList<>();
        investmentInstitutionsFundsSegmentationList = investmentInstitutionPreferSegmentationMapper.select(investmentInstitutionPreferSegmentation);
        return investmentInstitutionsFundsSegmentationList;
    }
}
