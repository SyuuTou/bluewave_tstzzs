package com.lhjl.tzzs.proxy.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.AssessmentDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TouZiDto;
import com.lhjl.tzzs.proxy.service.InvestorsApprovalService;
import com.lhjl.tzzs.proxy.service.ProjectEvaluationlogService;

import java.util.Map;

/**
 * Created by lmy on 2017/10/24.
 */
@RestController
public class ProjectEvaluationlogController {
	private static final Logger log = LoggerFactory.getLogger(ProjectEvaluationlogController.class);
	@Resource
	private ProjectEvaluationlogService projectEvaluationlogService ;
	/**
	 * 评估记录存储记录
	 * @param params
	 * @return
	 */
	@GetMapping("rest/zyy/cpinggujilu")
	 public CommonDto<String> insertGold(AssessmentDto params){
	     CommonDto<String>result = new CommonDto<String>();
	     try {
	     
	     result= projectEvaluationlogService.saveAssessment(params);
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
	 * /**
	 * 评估页面初次进入接口
	 * 评估页面再次进入回显接口
	 * @param token
	 * @return
	 */

	@GetMapping("rest/zyy/rlingyu")
	public CommonDto<Map<String,Object>> findInvestorsApproval(String token){
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();

		try {
			result = projectEvaluationlogService.findInvestorsApproval(token);
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