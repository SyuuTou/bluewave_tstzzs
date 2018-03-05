package com.lhjl.tzzs.proxy.controller.test;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lhjl.tzzs.proxy.dto.Test;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorSegmentationMapper;



/**
 * just for test , no other values
 * @author IdataVC
 *
 */
@RestController
public class TestDemo {
	@Resource
	private InvestorSegmentationMapper investorSegmentationMapper;
	@Resource
	private InvestmentInstitutionsMapper investmentInstitutionsMapper;
	
	@PostMapping("getSegmentations")
	public Object getSegmentations(@RequestBody Test body){
		System.err.println("body->"+body);
		return investorSegmentationMapper.getInvestorSegmentations(body.getSegs());
//		return "qwe";
	}
	
	@GetMapping("getId")
	public Object get() {
		Integer ids = investmentInstitutionsMapper.selectByCompanyName("隆领投资");
		return ids;
	}
}
