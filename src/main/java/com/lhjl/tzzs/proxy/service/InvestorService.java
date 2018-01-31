package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;

public interface InvestorService {
	/**
	 * 投资人列表
	 * @param appid
	 * @param body 投资人列表请求体
	 * @return
	 */
	CommonDto<Map<String,Object>> listInvestorsInfos(Integer appid, InvestorListInputDto body);


}
