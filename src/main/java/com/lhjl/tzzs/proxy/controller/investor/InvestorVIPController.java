package com.lhjl.tzzs.proxy.controller.investor;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.AdminUser;
import com.lhjl.tzzs.proxy.model.DatasOperationManage;
import com.lhjl.tzzs.proxy.service.InvestorService;

@RestController
public class InvestorVIPController extends GenericController {
	@Resource 
	private InvestorService investorService;
	
	/**
	 * 回显投资人会员信息
	 * @param appid
	 * @param id 投资人id
	 * @return
	 */
    @GetMapping("/v{appid}/echo/vipinfo")
    public CommonDto<DatasOperationManage> echoInvestorsVIPInfo(@PathVariable Integer appid,Integer id){
    	CommonDto<DatasOperationManage> result =new CommonDto<>();
    	try {
    		result = investorService.echoInvestorsManagementInfo(appid,id);
    	}catch(Exception e) {
    		this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
        return result;
    }
    /**
     * 更新或者保存投资人的运营管理
     * @param appid
     * @param body
     * @return
     */
    @PostMapping("/v{appid}/saveorupdate/vipinfo")
    public CommonDto<Boolean> saveOrUpdateInvestorsVIPInfo(@PathVariable Integer appid,@RequestBody DatasOperationManage body){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
//    		result = investorService.saveOrUpdateInvestorsVIPInfo(appid,body);
    	}catch(Exception e) {
    		this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
    		
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
        return result;
    }
    
}
