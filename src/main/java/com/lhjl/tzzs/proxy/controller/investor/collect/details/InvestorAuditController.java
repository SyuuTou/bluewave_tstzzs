package com.lhjl.tzzs.proxy.controller.investor.collect.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestorSpecialApprovalDto;
import com.lhjl.tzzs.proxy.dto.InvestorsApprovalActionDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorKernelInfoDto;
import com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditdetaildto.*;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.service.InvestorAuditService;
import com.lhjl.tzzs.proxy.service.InvestorBasicinfoService;
import com.lhjl.tzzs.proxy.service.InvestorInfoService;
import com.lhjl.tzzs.proxy.service.InvestorInvestInfoService;
import com.lhjl.tzzs.proxy.service.InvestorsApprovalService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/7.
 */
@RestController
public class InvestorAuditController extends GenericController{
	@Resource
	private InvestorsApprovalService investorsApprovalService;
	
	
	/**
	 * 后台审核操作接口(新)
	 * @param body
	 * @return
	 */
	@PostMapping("admin/approval")  
	public CommonDto<String> adminApproval(@RequestBody InvestorSpecialApprovalDto body){
		CommonDto<String> result = new CommonDto<>();
		try {
			result= investorsApprovalService.adminApproval(body);
		}catch (Exception e){
			this.LOGGER.error(e.getMessage(),e.fillInStackTrace());

			result.setMessage("服务器端发生错误");
			result.setData(null);
			result.setStatus(502);
		}

		return result;
 
	}
	/**
	 * 新后台用户审核接口
	 * @param body
	 * @return
	 */
	@PostMapping("/v{appid}/admin/special/approval")
	public CommonDto<String> adminSpecialApproval(@RequestBody InvestorSpecialApprovalDto body,Integer appid){
		CommonDto<String> result = new CommonDto<>();

		try {
			result = investorsApprovalService.adminSpecialApproval(body,appid);
		}catch (Exception e){
			this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
			result.setData(null);
			result.setStatus(502);
			result.setMessage("服务器端发生错误");
		}

		return result;
	}
	/**
	 * 后台审核操作接口
	 * @param body 请求对象
	 * @return
	 */
	@GetMapping("/v{appId}/approval")
	public CommonDto<String> approval(InvestorsApprovalActionDto body, @PathVariable Integer appId){
		CommonDto<String> result = new CommonDto<>();
		try {
			result = investorsApprovalService.approval(body,appId);
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("投资审核操作异常");
			this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
		}
		return result;
	}
	/**
	 * 后台审核操作接口
	 * @param body 请求对象
	 * @return
	 */
	@GetMapping("/approval")
	public CommonDto<String> approval(InvestorsApprovalActionDto body){
		CommonDto<String> result = new CommonDto<>();
		try {
			result = investorsApprovalService.approval(body,1);
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("投资审核操作异常");
			this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
		}
		return result;
	}
	
	 /**
		 * 用户列表页审核用户成为投资人的接口
		 * @param userId 用户id
	     * @param status 审核状态
		 * @param userName 用户名
		 * @param companyName 公司名称
		 * @param comanyDuties 公司职位
		 * @return
		 */
		@GetMapping("/special/approval")
		public CommonDto<String> specialApproval(Integer userId,Integer status,String userName,String companyName,String comanyDuties){
			CommonDto<String> result = new CommonDto<>();

			try {
				result = investorsApprovalService.specialApproval(userId,status,userName,companyName,comanyDuties,1);
			}catch (Exception e){
				this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
				result.setMessage("服务器端发生错误");
				result.setStatus(502);
				result.setData(null);
			}

			return result;
		}

		/**
		 * 用户列表页审核用户成为投资人的接口
		 * @param userId 用户id
	     * @param status 审核状态
		 * @param userName 用户名
		 * @param companyName 公司名称
		 * @param comanyDuties 公司职位
		 * @return
		 */
		@GetMapping("/v{appId}/special/approval")
		public CommonDto<String> specialApproval(Integer userId,Integer status,String userName,String companyName,String comanyDuties,@PathVariable Integer appId){
			CommonDto<String> result = new CommonDto<>();

			try {
				result = investorsApprovalService.specialApproval(userId,status,userName,companyName,comanyDuties,appId);
			}catch (Exception e){
				this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
				result.setMessage("服务器端发生错误");
				result.setStatus(502);
				result.setData(null);
			}

			return result;
		}

	
}
