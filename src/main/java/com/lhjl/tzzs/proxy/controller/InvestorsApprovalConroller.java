package com.lhjl.tzzs.proxy.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

	@Resource
	private UsersWeixinMapper usersWeixinMapper;

	/**
	 * 投资人记录信息
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
	     log.error(e.getMessage());
	     }
	     return result;
	     }

	/**
	 * 审核状态
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
	 * 认证页面
	 * @param token
	 * @return
	 */
	@GetMapping("rest/renzhengtouzirenshenhebiao/newrshenhexinxi")
	public CommonDto<Map<String,Object>> findInvestorsApproval(String token){
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();

		try {
			//初始化分页信息
			result = investorsApprovalService.findInvestorsApproval(token);
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
	 * 后台审核操作接口
	 * @param body 请求对象
	 * @return
	 */
	@GetMapping("/approval")
	public CommonDto<String> approval(InvestorsApprovalActionDto body){
		CommonDto<String> result = new CommonDto<>();
		try {
			result = investorsApprovalService.approval(body);
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("投资审核操作异常");
			log.error(e.getMessage());
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
	public CommonDto<String> sendApprovalLog(Integer id,Integer status){
		CommonDto<String> result = new CommonDto<>();

		UsersWeixin userswx = new UsersWeixin();
		userswx.setUserId(id);

		String kaitou = "";
		String leixing = "";
		String xiaoxi = "";
		if (status == 0 ){
			result.setData(null);
			result.setMessage("传入类型错误");
			result.setStatus(50001);

			return result;
		}

		if (status == 1 || status == 2){
			kaitou = "抱歉！";
		}else {
			kaitou = "恭喜您";
		}

		switch (status){
			case 1:leixing = "您的投资人认证未通过审核，请您重新填写";
			break;
			case 2:leixing = "您已被取消投资人资格";
			break;
			case 3:leixing = "您已被认证为个人投资人";
			break;
			case 4:leixing = "您已被认证为机构投资人";
			break;
			case 5:leixing = "您已被认证为vip投资人！";
			break;
		}

		xiaoxi = kaitou + leixing;

		String openId = "";
		List<UsersWeixin> usersWeixins = usersWeixinMapper.select(userswx);
		if (usersWeixins.size() > 0){
			openId = usersWeixins.get(0).getOpenid();
		}else {
			result.setData(null);
			result.setMessage("没有找到用户的openId信息");
			result.setStatus(50001);

			return result;
		}


		WxMaKefuMessage message = new WxMaKefuMessage();
		 message.setDescription("恭喜");
		 message.setMsgType("text");
		 message.setContent(xiaoxi);
		 message.setToUser(openId);

		 try {
		 	//wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.newBuilder().toUser(openId).data().build());
			 wxService.getMsgService().sendKefuMsg(message);
			 result.setStatus(200);
			 result.setMessage("发送成功");
			 result.setData(null);

		 }catch (WxErrorException e){
		 	log.info(e.getLocalizedMessage());
		 	result.setData(null);
		 	result.setMessage("服务器端发生错误");
		 	result.setStatus(502);

		 }


		return result;
	}
}
