package com.lhjl.tzzs.proxy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.lhjl.tzzs.proxy.dto.*;

import com.lhjl.tzzs.proxy.mapper.UsersWeixinMapper;
import com.lhjl.tzzs.proxy.model.InvestorsApprovalNew;

import com.lhjl.tzzs.proxy.model.UsersWeixin;

import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.lhjl.tzzs.proxy.service.InvestorsApprovalService;
@RestController
public class InvestorsApprovalConroller {
	private static final Logger log = LoggerFactory.getLogger(InvestorsApprovalConroller.class);
	@Resource
	private InvestorsApprovalService investorsApprovalService;

	@Value("${pageNum}")
	private String defaultPageNum;

	@Value("${pageSize}")
	private String defaultPageSize;

	@Autowired
	private WxMaService wxService;



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
		     log.error(e.getMessage(), e);
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
			//初始化分页信息
			result = investorsApprovalService.findInvestorsExamine(token);
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
			log.error(e.getMessage(), e);
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
			log.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 获取投资审核信息列表(后台调用)(新)
	 * @param body
	 * @return
	 */
	@PostMapping("admin/findinvestorsapproval")
	public CommonDto<Map<String,Object>> adminFindApprovals(@RequestBody InvestorsApprovalInputDto body){
		CommonDto<Map<String,Object>> result = new CommonDto<>();

		try {
			result = investorsApprovalService.adminFindApprovals(body);
		}catch (Exception e){
			log.error(e.getMessage(),e.fillInStackTrace());
			result.setStatus(502);
			result.setData(null);
			result.setMessage("服务器端发生错误");

			return result;
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
			log.error(e.getMessage(),e.fillInStackTrace());
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
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		return result;
	}


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
			log.error(e.getMessage(),e.fillInStackTrace());

			result.setMessage("服务器端发生错误");
			result.setData(null);
			result.setStatus(502);
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
			log.error(e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}


	@GetMapping("/send/approvallog")
	public CommonDto<String> sendApprovalLog(Integer userId,Integer status,String formId){
		CommonDto<String> result = new CommonDto<>();


		result = investorsApprovalService.sendTemplate(userId,status,formId);




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
			log.error(e.getMessage(),e.fillInStackTrace());
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
			log.error(e.getMessage(),e.fillInStackTrace());
			result.setMessage("服务器端发生错误");
			result.setStatus(502);
			result.setData(null);
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
			log.error(e.getMessage(),e.fillInStackTrace());
			result.setData(null);
			result.setStatus(502);
			result.setMessage("服务器端发生错误");
		}

		return result;
	}
}
