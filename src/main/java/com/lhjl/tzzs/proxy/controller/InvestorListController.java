package com.lhjl.tzzs.proxy.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;

@RestController
public class InvestorListController {
	/**
	 * 投资人列表
	 * @param sendsecuritycodeReqBody
	 * @return
	 */
    @PostMapping("/v{appid}/list/investors")
    public CommonDto<Object> getInvestorsInfo(@PathVariable Integer appid,@RequestBody InvestorListInputDto body){
    	CommonDto<Object> result =new CommonDto<>();
    	try {
    		
    	}catch(Exception e) {
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
        return result;
    }
}
