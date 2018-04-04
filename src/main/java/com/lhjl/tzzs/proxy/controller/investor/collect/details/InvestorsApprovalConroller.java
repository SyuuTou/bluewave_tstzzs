package com.lhjl.tzzs.proxy.controller.investor.collect.details;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.model.InvestorsApprovalNew;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.lhjl.tzzs.proxy.service.InvestorsApprovalService;
@RestController
public class InvestorsApprovalConroller extends GenericController {
	@Resource
	private InvestorsApprovalService investorsApprovalService;

	@Value("${pageNum}")
	private String defaultPageNum;

	@Value("${pageSize}")
	private String defaultPageSize;

	/**
	 * 认证信息回显接口
	 * @param token
	 * @return
	 */
	@GetMapping("rest/renzhengtouzirenshenhebiao/newrshenhexinxi")
	public CommonDto<Map<String,Object>> findInvestorsApproval(String token){
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();

		try {
			result = investorsApprovalService.findInvestorsApproval(token);
			if(result.getStatus() == null){
				result.setStatus(200);
				result.setMessage("success");
			}
		} catch (Exception e) {

			result.setStatus(5101);
			result.setMessage("显示页面异常，请稍后再试");
			this.LOGGER.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 通过token获取投资人审核状态
	 * @param token
	 * @return
	 */
	@GetMapping("rest/user/newryhxinxia")
	public CommonDto<Map<String,Object>> findInvestorsExamine(String token){
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();
		
		try {
			result = investorsApprovalService.findInvestorsExamine(token);
			if(result.getStatus() == null){
				result.setStatus(200);
				result.setMessage("success");
			}
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("显示页面异常，请稍后再试");
			this.LOGGER.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 获取投资审核信息(后台调用)
	 * @return
	 */
	@GetMapping("/findinvestorsapproval")
	public CommonDto<List<InvestorsApprovalNew>> findApprovals(InvestorsApprovalDto body){
		CommonDto<List<InvestorsApprovalNew>>result = new CommonDto<>();
		Integer pageNum = body.getPageNum();
		Integer pageSize = body.getPageSize();
		try {
			//初始化分页信息
			if(pageNum == null){
				pageNum = Integer.parseInt(defaultPageNum);
			}
			if(pageSize == null){
				pageSize = Integer.parseInt(defaultPageSize);
			}
			body.setPageNum(pageNum);
			body.setPageSize(pageSize);
			result = investorsApprovalService.findApprovals(body);
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("获取投资审核信息异常");
			this.LOGGER.error(e.getMessage());
		}
		return result;
	}

	@GetMapping("/send/approvallog")  
	public CommonDto<String> sendApprovalLog(Integer userId,Integer status,String formId){
		CommonDto<String> result = new CommonDto<>();

		result = investorsApprovalService.sendTemplate(userId,status,formId);

		return result;
	}
	
}
