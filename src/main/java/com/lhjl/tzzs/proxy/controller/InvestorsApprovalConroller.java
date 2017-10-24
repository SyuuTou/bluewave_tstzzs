package com.lhjl.tzzs.proxy.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.MingDto;
import com.lhjl.tzzs.proxy.dto.TouZiDto;
import com.lhjl.tzzs.proxy.service.InvestorsApprovalService;
@RestController
public class InvestorsApprovalConroller {
	private static final Logger log = LoggerFactory.getLogger(InvestorsApprovalConroller.class);
	@Resource
	private InvestorsApprovalService investorsApprovalService;
	/**
	 * 投资人记录信息
	 * @param params
	 * @return
	 */
	
	@GetMapping("rest/user/newuyhxinxi1")
	 public CommonDto<String> insertGold( TouZiDto params){
	     CommonDto<String>result = new CommonDto<String>();
	     try {
	     
	     result=investorsApprovalService.saveTouZi(params);
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
	 * 审核状态
	 * @param userID
	 * @return
	 */
	@GetMapping("rest/user/newryhxinxia")
	public CommonDto<Map<String,Object>> findInvestorsExamine(String userID){
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();
		
		try {
			//初始化分页信息
			result = investorsApprovalService.findInvestorsExamine(userID);
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
	 * 认证页面
	 * @param userID
	 * @return
	 */
	@GetMapping("rest/renzhengtouzirenshenhebiao/newrshenhexinxi")
	public CommonDto<Map<String,Object>> findInvestorsApproval(String userID){
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();

		try {
			//初始化分页信息
			result = investorsApprovalService.findInvestorsApproval(userID);
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
