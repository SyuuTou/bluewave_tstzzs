package com.lhjl.tzzs.proxy.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.CommonDto;

public interface InvestmentDatalogService {
	CommonDto<String> saveInvestmentData(String investment_institution_name , String project_name, String project_full_name, String summary, String field,  String city, String rounds, String amount,  String currency,  String stock_right,  Date date,  String founder_name, String founder_work, String founder_education,String userId,String zhiwu);
	/**
	 * 查询数据提交
	 * @param uuids
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	CommonDto<List<Map<String, Object>>>saveInformation(String uuids, Integer pageNum, Integer pageSize);

	CommonDto<Map<String, Object>> findinformation(Integer id);
	
	
	CommonDto<String> saveInvestmentData1(String investment_institution_name , String project_name, String project_full_name, String summary, String field,  String city, String rounds, String amount,  String currency,  String stock_right,  Date dateDate,  String founder_name, String founder_work, String founder_education,String userId,String zhiwu,Integer id);
}
