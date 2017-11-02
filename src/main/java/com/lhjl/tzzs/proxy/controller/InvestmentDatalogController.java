package com.lhjl.tzzs.proxy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.dto.InformationDto;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentDataDto;
import com.lhjl.tzzs.proxy.service.InvestmentDataService;
import com.lhjl.tzzs.proxy.service.InvestmentDatalogService;

import tk.mybatis.mapper.util.StringUtil;
@RestController
public class InvestmentDatalogController {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentDatalogController.class);


	@Resource
	private InvestmentDatalogService investmentDatalogService;

	/**
	 * 创建提交投资数据接口
	 * @param body
	 * @return
	 */
	@PostMapping("investment/data/log")
	public CommonDto<String> saveInvestmentData(@RequestBody InvestmentDataDto body){
		CommonDto<String> result = new CommonDto<String>();

		String investment_institution_name = body.getInvestment_institution_name();
		String project_name = body.getProject_name();
		String project_full_name = body.getProject_full_name();
		String summary = body.getSummary();
		String field = body.getField();
		String city =body.getCity();
		String rounds = body.getRounds();
		String amount = body.getAmount();
		String currency = body.getCurrency();
		String stock_right = body.getStock_right();
		String date = body.getDate();
		String founder_name = body.getFounder_name();
		String founder_work = body.getFounder_work();
		String founder_education = body.getFounder_education();
		String userId = body.getUserId();



		try {
			SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
			Date dateDate = null;
			if (!StringUtil.isEmpty(date)) {
				dateDate = simpleDateFormat.parse(date);
			}
			result = investmentDatalogService.saveInvestmentData(investment_institution_name,project_name,project_full_name,summary,field,city,rounds,amount,currency,stock_right,dateDate,founder_name,founder_work,founder_education,userId);
			result.setStatus(200);
			result.setMessage("success");
		}catch (Exception e){
			result.setMessage(e.getMessage());
			result.setStatus(501);
			result.setData(null);
			log.error(e.getMessage(),e.fillInStackTrace());
		}


		return result;
	}
	/**
	 * 查询提交数据的代码
	 * @param body
	 * @return
	 */
	@PostMapping("search/data/information")
	public CommonDto<List<Map<String,Object>>> saveInformation(@RequestBody InformationDto body){
		CommonDto<List<Map<String,Object>>> result = new CommonDto<List<Map<String,Object>>>();
		Integer pageNum = body.getPageNum();
		Integer pageSize = body.getPageSize();
		try {
			//初始化分页信息
			if(pageNum == null){
				pageNum = 1;
			}
			if(pageSize == null){
				pageSize = 20;
			}
			String uuids =body.getUuids();
			result = investmentDatalogService.saveInformation(uuids,pageNum,pageSize);

			if(result.getStatus() == null){
				result.setStatus(200);
				result.setMessage("success");
			}
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("显示页面异常，请稍后再试");
			log.error(e.getMessage());
		}
		return result;
	}
	/**
	 * 数据的回显
	 * @param id
	 * @return
	 */
	@GetMapping("information/record")
	public CommonDto<Map<String, Object>> findinformation(Integer id){
		CommonDto<Map<String, Object>>  result = new CommonDto<Map<String, Object>>();

		try {
			result =investmentDatalogService.findinformation(id);
			if(result.getStatus() == null){
				result.setStatus(200);
				result.setMessage("success");
			}
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("显示页面异常，请稍后再试");
			log.error(e.getMessage());
		}
		return result;
	}
}
