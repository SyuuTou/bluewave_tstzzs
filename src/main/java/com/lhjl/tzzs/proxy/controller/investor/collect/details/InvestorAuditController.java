package com.lhjl.tzzs.proxy.controller.investor.collect.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestorSpecialApprovalDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorKernelInfoDto;
import com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditdetaildto.*;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.service.InvestorAuditService;
import com.lhjl.tzzs.proxy.service.InvestorBasicinfoService;
import com.lhjl.tzzs.proxy.service.InvestorInfoService;
import com.lhjl.tzzs.proxy.service.InvestorInvestInfoService;
import com.lhjl.tzzs.proxy.service.InvestorsApprovalService;

import org.springframework.web.bind.annotation.GetMapping;
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
}
