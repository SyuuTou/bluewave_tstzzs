package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;

public interface InvestorService {
	
	CommonDto<Object> listInvestorsInfos(Integer appid, InvestorListInputDto body);

}
