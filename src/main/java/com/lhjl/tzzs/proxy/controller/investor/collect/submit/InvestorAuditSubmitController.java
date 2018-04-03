package com.lhjl.tzzs.proxy.controller.investor.collect.submit;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TouZiDto;
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
public class InvestorAuditSubmitController extends GenericController{
	@Resource
	private InvestorsApprovalService investorsApprovalService;
	
	@Resource
    private InvestorsApprovalNewService investorsApprovalNewService;
	
	/**
	 * 申请成为投资人接口
	 * @param params
	 * @return
	 */
	
	@PostMapping("rest/user/newuyhxinxi1")
	 public CommonDto<String> insertGold( @RequestBody  TouZiDto params){
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
		     this.LOGGER.error(e.getMessage(), e);
	     }
	     return result;
     }
	
	/**
     * 1.2.4 投资人记录信息
     * @param params
     * @return
     */

    @PostMapping("1_2_4/rest/user/newuyhxinxi1")
    public CommonDto<String> insertGoldNew(@RequestBody TouZiNewDto params){
        CommonDto<String>result = new CommonDto<String>();
        try {

            result=investorsApprovalNewService.saveTouZiNew(params);
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
