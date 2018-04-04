package com.lhjl.tzzs.proxy.controller.investor.collect.list;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestorsApprovalInputDto;
import com.lhjl.tzzs.proxy.dto.InvestorsApprovalOutputDto;
import com.lhjl.tzzs.proxy.dto.PagingOutputDto;
import com.lhjl.tzzs.proxy.dto.TouZiNewDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorKernelInfoDto;
import com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditdetaildto.*;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.service.InvestorAuditService;
import com.lhjl.tzzs.proxy.service.InvestorBasicinfoService;
import com.lhjl.tzzs.proxy.service.InvestorInfoService;
import com.lhjl.tzzs.proxy.service.InvestorInvestInfoService;
import com.lhjl.tzzs.proxy.service.InvestorsApprovalNewService;
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
public class InvestorAuditListController extends GenericController{
	@Resource
	private InvestorsApprovalService investorsApprovalService;
	
	/**
	 * 获取投资审核信息列表(后台调用)(新)
	 * @param body
	 * @return
	 */
	@PostMapping("admin/findinvestorsapproval")
	public CommonDto<PagingOutputDto<InvestorsApprovalOutputDto>> adminFindApprovals(@RequestBody InvestorsApprovalInputDto body){
		CommonDto<PagingOutputDto<InvestorsApprovalOutputDto>> result = new CommonDto<>();

		try {
			result = investorsApprovalService.adminFindApprovals(body);
		}catch (Exception e){
			this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
			result.setStatus(502);
			result.setData(null);
			result.setMessage("服务器端发生错误");

			return result;
		}

		return result;
	}
	
	/**
	 * 获取工作名片
	 * @param approvalId 投资审核记录ID
	 * @return
	 */
	@GetMapping("/workcard")
	public CommonDto<String> getWorkcard(String approvalId){
		CommonDto<String> result = new CommonDto<>();
		try{
			result = investorsApprovalService.getWorkcard(approvalId);
		}catch(Exception e){
			result.setStatus(5010);
			result.setMessage("获取工作名片异常");
			this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
}
