package com.lhjl.tzzs.proxy.controller.test;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lhjl.tzzs.proxy.dto.Test;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorSegmentationMapper;
import com.lhjl.tzzs.proxy.model.InvestorSegmentation;
import com.lhjl.tzzs.proxy.service.InvestorSegmentationService;



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
	
	@Resource
	private InvestorSegmentationService investorSegmentationService;
	
	@PostMapping("getSegmentations")
	public Object getSegmentations(@RequestBody Test body){
		System.err.println("body->"+body);
		return investorSegmentationMapper.getInvestorSegmentations(body.getSegs());
//		return "qwe";
	}
	//测试事务
	@PostMapping("test/Transantion")
	public Object testTransantion(@RequestBody InvestorSegmentation body){
		Integer result=-1;
		try {
			result = investorSegmentationService.edit(body);
		}catch(Exception e) {
			return "异常发生";
		}
		
		return result;
	}
	
	@GetMapping("getId")
	public Object get() {
		Integer ids=-1;
		try{
			 ids = investmentInstitutionsMapper.selectByCompanyName("隆领投资");
			
		}catch(Exception e) {
			e.printStackTrace();
			return "false";
		}
		return ids;
	}
}
