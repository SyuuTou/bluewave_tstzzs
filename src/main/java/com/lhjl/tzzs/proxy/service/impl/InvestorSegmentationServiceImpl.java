package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestorSegmentationMapper;
import com.lhjl.tzzs.proxy.model.InvestorSegmentation;
import com.lhjl.tzzs.proxy.service.InvestorSegmentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class InvestorSegmentationServiceImpl implements InvestorSegmentationService{

    @Autowired
    private InvestorSegmentationMapper investorSegmentationMapper;

    @Override
    public Integer save(InvestorSegmentation investorSegmentation) {
        return investorSegmentationMapper.insert(investorSegmentation);
    }

    @Override
    public void deleteAll(Integer investorId) {
        InvestorSegmentation investorSegmentation = new InvestorSegmentation();
        investorSegmentation.setId(investorId);
        investorSegmentationMapper.delete(investorSegmentation);
    }

    @Override
    public void delete(InvestorSegmentation investorSegmentation) {
        investorSegmentationMapper.delete(investorSegmentation);
    }

    @Override
    public Integer insertList(List<InvestorSegmentation> investorSegmentationList) {
        return investorSegmentationMapper.insertList(investorSegmentationList);
    }

    @Override
    public List<InvestorSegmentation> select(InvestorSegmentation investorSegmentation) {
        return investorSegmentationMapper.select(investorSegmentation);
    }
    
    @Transactional
	@Override
	public Integer edit(InvestorSegmentation body)  {
		int i = investorSegmentationMapper.insertSelective(body);
//		int b =1/0;
		return i;
	}
}
