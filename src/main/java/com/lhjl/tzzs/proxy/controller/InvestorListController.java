package com.lhjl.tzzs.proxy.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;
import com.lhjl.tzzs.proxy.service.InvestorService;

@RestController
public class InvestorListController extends GenericController {
	@Resource 
	private InvestorService investorService;
	
	/**
	 * 投资人列表
	 * @param appid
	 * @param body 投资人列表请求体
	 * @return
	 */
    @PostMapping("/v{appid}/list/investors")
    public CommonDto<Map<String,Object>> listInvestorsInfo(@PathVariable Integer appid,@RequestBody InvestorListInputDto body){
    	CommonDto<Map<String,Object>> result =new CommonDto<>();
    	try {
    		result = investorService.listInvestorsInfos(appid,body);
    	}catch(Exception e) {
    		this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
        return result;
    }
}
