package com.lhjl.tzzs.proxy.service;

import java.util.Map;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;

public interface InvestorService {
	
	CommonDto<Map<String,Object>> listInvestorsInfos(Integer appid, InvestorListInputDto body);

}
