package com.lhjl.tzzs.proxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.InvestorSegmentation;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface InvestorSegmentationMapper extends OwnerMapper<InvestorSegmentation> {
	InvestorSegmentation[] getInvestorSegmentations(@Param(value = "segmentation") Integer[] segmentation);
	
}