package com.lhjl.tzzs.proxy.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.lhjl.tzzs.proxy.dto.InvestorsApprovalActionDto;
import com.lhjl.tzzs.proxy.dto.InvestorsApprovalDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.UserLevelService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TouZiDto;
import com.lhjl.tzzs.proxy.service.InvestorsApprovalService;

@Service
public class InvestorsApprovalserviceImpl implements InvestorsApprovalService {

	private static final Logger log = LoggerFactory.getLogger(InvestorsApprovalService.class);

	@Resource
	private InvestorsApprovalMapper investorsApprovalMapper;
	
	@Resource
	private UserTokenMapper userTokenMapper;
	@Resource
	private UsersMapper usersMapper;
	@Resource

	private InvestorsMapper investorsMapper;
	@Resource
	private UserLevelService userLevelService;
	@Resource
	private UsersWeixinMapper usersWeixinMapper;
	@Resource
    private InvestorInvestmentCaseMapper investorInvestmentCaseMapper;

	@Autowired
	private WxMaService wxService;
    
	/**
	 * 认证投资人信息记录
	 */
	@Transactional
	@Override
	public CommonDto<String> saveTouZi(TouZiDto params) {
		CommonDto<String> result =new CommonDto<String>();
		try {
			//先取出参数进行验证
			String token = params.getToken();
			String compellation = params.getCompellation();
			String dataName = params.getDateName();
			String company = params.getOrganization();
			String companyDuties = params.getFillOffice();
			String formId = params.getFormId();

			if ("".equals(token) || token == null || "undefined".equals(token)){
                result.setData(null);
                result.setMessage("用户token不能为空");
                result.setStatus(50001);

                log.info("投资人认证场景");
                log.info("用户token不能为空");

                return result;
            }

			if ("".equals(compellation) || compellation == null || "undefined".equals(compellation)){
                result.setData(null);
                result.setMessage("请填写姓名");
                result.setStatus(50001);

				log.info("投资人认证场景");
				log.info("请填写姓名");

                return result;
            }

			/*if ("".equals(dataName) || dataName == null || "undefined".equals(dataName)){
                result.setData(null);
                result.setMessage("请选择投资人类型");
                result.setStatus(50001);

				log.info("投资人认证场景");
				log.info("请选择投资人类型");

                return result;
            }*/


			if ("".equals(company) || company == null || "undefined".equals(company)){
                result.setData(null);
                result.setMessage("请填写所在公司");
                result.setStatus(50001);

				log.info("投资人认证场景");
				log.info("请填写所在公司");

                return result;
            }

			if ("".equals(companyDuties) || companyDuties == null || "undefined".equals(companyDuties)){
                result.setData(null);
                result.setMessage("请填写所在公司职务");
                result.setStatus(50001);

				log.info("投资人认证场景");
				log.info("请填写所在公司职务");

                return result;
            }

			if ("".equals(formId) || formId == null || "undefined".equals(formId)){
                result.setData(null);
                result.setMessage("formId不存在");
                result.setStatus(50001);

				log.info("投资人认证场景");
				log.info("formId不存在");

                return result;
            }


			InvestorsApproval  investorsApproval =new  InvestorsApproval();
			UserToken userToken =new UserToken();
			userToken.setToken(params.getToken());
			userToken =userTokenMapper.selectOne(userToken);
			investorsApproval.setUserid(userToken.getUserId());
			investorsApproval.setApprovalUsername(params.getCompellation());
//		 if("个人投资人".equals(params.getDateName())){
            if(dataName == null  ||  "".equals(dataName)){

            }else{
				investorsApproval.setInvestorsType(Integer.valueOf(dataName));
			}
//		 }
//		 if("机构投资人".equals(params.getDateName())){
//			 investorsApproval.setInvestorsType(1);
//			 }
//		 if("VIP投资人".equals(params.getDateName())){
//			 investorsApproval.setInvestorsType(2);
//			 }
			investorsApproval.setDescription(params.getEvaContent());
			investorsApproval.setCompany(params.getOrganization());
			investorsApproval.setCompanyDuties(params.getFillOffice());
			investorsApproval.setWorkCard(params.getTempFilePaths());
			investorsApproval.setApprovalResult(0);
			investorsApproval.setReviewTime(new Date());
			investorsApproval.setCreateTime(new Date());
			investorsApproval.setFormId(formId);
			investorsApproval.setInvestorsApprovalcolCase(params.getInvestorsApprovalcolCase());

			investorsApprovalMapper.insert(investorsApproval);
			Users users =new Users();
			users.setUuid(params.getToken());
			Users u =usersMapper.selectOne(users);
			u.setCompanyName(params.getOrganization());
			u.setCompanyDuties(params.getFillOffice());
			u.setActualName(params.getCompellation());
			u.setWorkCard(params.getTempFilePaths());
			usersMapper.updateByPrimaryKey(u);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
  /**
   * 数据的回显
   */
	@Override
	public CommonDto<Map<String,Object>> findInvestorsApproval(String token) {
		CommonDto<Map<String, Object>> result =new CommonDto<Map<String, Object>> ();
		try {
			Map<String,Object> map =new HashMap<>();
			UserToken userToken =new UserToken();
			userToken.setToken(token);
			userToken = userTokenMapper.selectOne(userToken);
			Users users1 =new Users();
            users1.setUuid(token);
            users1 = usersMapper.selectOne(users1);

			Integer userId = userToken.getUserId();
			map=investorsApprovalMapper.findInvestorsApproval(userId);
			String anli = "";
			if (map == null){
                anli = "";
            }else{
                if (map.get("investors_approvalcol_case") == null){
                    anli = "";
                }else{
                    anli = String.valueOf(map.get("investors_approvalcol_case"));
                }
            }


			String[] anliArray = anli.split(",");
			if(map !=null){

               map.put("id", token);
               map.put("renzhengtouzirenshenhebiao7additional","");//其他说明
               map.put("renzhengtouzirenshenhebiao7applicantn",String.valueOf(map.get("approval_username")));
               map.put("renzhengtouzirenshenhebiao7assumeoffi",String.valueOf(map.get("company_duties")));
               map.put("renzhengtouzirenshenhebiao7certificat",String.valueOf(map.get("company")));
               map.put("renzhengtouzirenshenhebiao7frontofbus",users1.getWorkCard());
               map.put("renzhengtouzirenshenhebiao7wherecompa",String.valueOf(map.get("description")));
               map.put("investorsApprovalcolCase",anliArray);

               Map<String,Object> renzhenleixin =new HashMap<>();
               if (null != map.get("investors_type")) {
				   if (Integer.valueOf(String.valueOf(map.get("investors_type"))) == 0) {
					   renzhenleixin.put("0", true);
					   renzhenleixin.put("1", false);
					   renzhenleixin.put("2", false);
				   }
				   if (Integer.valueOf(String.valueOf(map.get("investors_type"))) == 1) {
					   renzhenleixin.put("0", false);
					   renzhenleixin.put("1", true);
					   renzhenleixin.put("2", false);
				   }
				   if (Integer.valueOf(String.valueOf(map.get("investors_type"))) == 2) {
					   renzhenleixin.put("0", false);
					   renzhenleixin.put("1", false);
					   renzhenleixin.put("2", true);
				   }
			   }else {
				   renzhenleixin.put("0", false);
				   renzhenleixin.put("1", false);
				   renzhenleixin.put("2", false);
			   }
               map.put("renzhenleixin",renzhenleixin);
            }else{
                   map=new HashMap<>();
                   Users users =new Users();
                   users.setUuid(token);
                   users = usersMapper.selectOne(users);
                   String username =users.getActualName();

                   map.put("id", token);
                   map.put("renzhengtouzirenshenhebiao7applicantn",username);
                   map.put("renzhengtouzirenshenhebiao7additional",null);//其他说明
                   map.put("renzhengtouzirenshenhebiao7assumeoffi",users.getCompanyDuties());
                   map.put("renzhengtouzirenshenhebiao7certificat",users.getCompanyName());
                   map.put("renzhengtouzirenshenhebiao7frontofbus",users.getWorkCard());
                   map.put("renzhengtouzirenshenhebiao7wherecompa",null);
                   map.put("investorsApprovalcolCase",anliArray);
                   Map<String,Object> renzhenleixin =new HashMap<>();
                   renzhenleixin.put("0",false);
                   renzhenleixin.put("1",false);
                   renzhenleixin.put("2",false);
                   map.put("renzhenleixin",renzhenleixin);
            }
			result.setData(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 进入审核状态的
	 */

	@Override
	public CommonDto<Map<String, Object>> findInvestorsExamine(String token) {
		CommonDto<Map<String, Object>> result =new CommonDto<Map<String, Object>> ();

		if (token == null || "".equals(token) || "undefined".equals(token)){
			Map<String,Object> obj = new HashMap<>();
			obj.put("success",false);
			obj.put("tips","用户token不能为空");

			result.setStatus(50001);
			result.setMessage("用户token不能为空");
			result.setData(obj);

			return result;

		}

		Map<String,Object> map =new HashMap<>();
		UserToken userToken =new UserToken();
		 userToken.setToken(token);
		 userToken = userTokenMapper.selectOne(userToken);
		 Integer userId = userToken.getUserId();
		 //判断用户token是否有效
		if(userId == null ){
			Map<String,Object> obj = new HashMap<>();
			obj.put("success",false);
			obj.put("tips","用户token无效请检查");

			result.setData(obj);
			result.setMessage("用户token无效请检查");
			result.setStatus(50001);

			return result;
		}

		 map=investorsApprovalMapper.findInvestorsApproval(userId);
		 if(map != null){
			 if(Integer.valueOf(String.valueOf(map.get("approval_result")))==0){
				 map.put("gongsizhiwei",String.valueOf(map.get("company_duties")));
					map.put("gongsimingcheng",String.valueOf(map.get("company")));
					map.put("touzirenleixing",null);
					map.put("shenhewancheng", false);
					map.put("shenhezhong",true);
					map.put("weitongguo", false);
					map.put("yiquxiao", false); 
					map.put("success",true);
					}
			 if(Integer.valueOf(String.valueOf(map.get("approval_result")))==1){
				 map.put("gongsizhiwei",String.valueOf(map.get("company_duties")));
					map.put("gongsimingcheng",String.valueOf(map.get("company")));
					map.put("touzirenleixing",null);
					map.put("shenhewancheng", false);
					map.put("shenhezhong",false);
					map.put("weitongguo", true);
					map.put("yiquxiao", false); 
					map.put("success",true);	
			 }
			 if(Integer.valueOf(String.valueOf(map.get("approval_result")))==3){
				 map.put("gongsizhiwei",String.valueOf(map.get("company_duties")));
					map.put("gongsimingcheng",String.valueOf(map.get("company")));
				    map.put("touzirenleixing","个人投资人");
					map.put("shenhewancheng",true);
					map.put("shenhezhong",false);
					map.put("weitongguo", false);
					map.put("yiquxiao", false); 
					map.put("success",true);	
			 }
			 if(Integer.valueOf(String.valueOf(map.get("approval_result")))==4){
				 map.put("gongsizhiwei",String.valueOf(map.get("company_duties")));
					map.put("gongsimingcheng",String.valueOf(map.get("company")));
				    map.put("touzirenleixing","机构投资人");
				    map.put("shenhewancheng",true);
					map.put("shenhezhong",false);
					map.put("weitongguo",false);
					map.put("yiquxiao", false); 
					map.put("success",true);
			 }
			 if(Integer.valueOf(String.valueOf(map.get("approval_result")))==5){
				 map.put("gongsizhiwei",String.valueOf(map.get("company_duties")));
					map.put("gongsimingcheng",String.valueOf(map.get("company")));
				    map.put("touzirenleixing","VIP投资人");
				    map.put("shenhewancheng",true);
					map.put("shenhezhong",false);
					map.put("weitongguo",false);
					map.put("yiquxiao", false); 
					map.put("success",true);
			 }
		 }else{
		    map=new HashMap<>();
			map.put("success",true);
			 map.put("shenhewancheng", false);
			 map.put("shenhezhong",false);
			 map.put("weitongguo",false);
			 map.put("yiquxiao", false);
		 }
		result.setData(map);
		return result;
	}

	/**
	 * 获取投资审核信息
	 * @param body 查询条件
	 * @return
	 */
	@Override
	public CommonDto<List<InvestorsApprovalNew>> findApprovals(InvestorsApprovalDto body) {
		CommonDto<List<InvestorsApprovalNew>> result = new CommonDto<>();
		List<InvestorsApproval> data = new ArrayList<>();
		List<InvestorsApprovalNew> dataNew = new ArrayList<>();
		int beginNum = (body.getPageNum()-1)*body.getPageSize();
		data = investorsApprovalMapper.findApproval(body.getCheckName(), body.getTime(), beginNum, body.getPageSize());
		for(InvestorsApproval approval : data){
			InvestorsApprovalNew investorsApprovalNew = new InvestorsApprovalNew();
			investorsApprovalNew.setId(approval.getId());
			investorsApprovalNew.setApprovalUsername(approval.getApprovalUsername());
			investorsApprovalNew.setApprovalResult(approval.getApprovalResult());
			investorsApprovalNew.setCompany(approval.getCompany());
			investorsApprovalNew.setCompanyDuties(approval.getCompanyDuties());
			investorsApprovalNew.setDescription(approval.getDescription());
			investorsApprovalNew.setInvestorsType(approval.getInvestorsType());
			investorsApprovalNew.setLeadership(approval.getLeadership());
			investorsApprovalNew.setSupplementaryExplanation(approval.getSupplementaryExplanation());
			investorsApprovalNew.setUserid(approval.getUserid());
			investorsApprovalNew.setWorkCard(approval.getWorkCard());
			investorsApprovalNew.setFormId(approval.getFormId());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime = simpleDateFormat.format(approval.getCreateTime());
			String reviewTime = simpleDateFormat.format(approval.getReviewTime());
			investorsApprovalNew.setCreateTime(createTime);
			investorsApprovalNew.setReviewTime(reviewTime);
			investorsApprovalNew.setInstitutionId(approval.getInstitutionId());
			dataNew.add(investorsApprovalNew);
		}
		result.setStatus(200);
		result.setMessage("获取投资审核信息成功");
		result.setData(dataNew);
		return result;
	}

	/**
	 * 后台审核操作接口
	 * @param body 请求对象
	 * @return
	 */
	@Override
	public CommonDto<String> approval(InvestorsApprovalActionDto body) {
		CommonDto<String> result = new CommonDto<>();
		int approvalId = body.getId();
		String approveResult = body.getApproveResult();
		String explanation = body.getExplanation();
		String approvalStatus = body.getApprovalStatus();

		//更新投资审核记录表
		InvestorsApproval approval = new InvestorsApproval();
		approval.setId(approvalId);
		approval = investorsApprovalMapper.selectOne(approval);
		approval.setApprovalResult(Integer.parseInt(approveResult));
		if(explanation != null && !"".equals(explanation)){
			approval.setSupplementaryExplanation(explanation);
		}
		investorsApprovalMapper.updateByPrimaryKey(approval);

		//更新投资人信息
		int userId = approval.getUserid();
		Investors investors = new Investors();
		investors.setUserId(userId);
		investors = investorsMapper.selectOne(investors);

		if(investors != null){
			investors.setApprovalStatus(Integer.parseInt(approvalStatus));

			switch(Integer.parseInt(approveResult)){
				case 3:
					investors.setInvestorsType(0);
					break;
				case 4:
					investors.setInvestorsType(1);
					break;
				case 5:
					investors.setInvestorsType(2);
					break;
				default:
					investors.setInvestorsType(null);
			}

			//升级为VIP投资人
			if(investors.getInvestorsType() == 2 || investors.getInvestorsType() == 1){
				UserToken userToken = new UserToken();
				userToken.setUserId(userId);
				userToken = userTokenMapper.selectOne(userToken);
				userLevelService.upLevel(userToken.getToken(), 4, null);
			}


			investorsMapper.updateByPrimaryKey(investors);

			//获取到申请人的投资人id
			int investorsId = investors.getId();

			//先删除已经存在的投资案例
            InvestorInvestmentCase investorInvestmentCaseForBefore = new InvestorInvestmentCase();
            investorInvestmentCaseForBefore.setInvestorId(investorsId);

            investorInvestmentCaseMapper.delete(investorInvestmentCaseForBefore);


		}else{
			Investors newInvestors = new Investors();
			newInvestors.setUserId(userId);
			newInvestors.setName(approval.getApprovalUsername());
			switch(Integer.parseInt(approveResult)){
				case 3:
					newInvestors.setInvestorsType(0);
					break;
				case 4:
					newInvestors.setInvestorsType(1);
					break;
				case 5:
					newInvestors.setInvestorsType(2);
					break;
				default:
					newInvestors.setInvestorsType(null);
			}
			//升级为VIP投资人
			if(newInvestors.getInvestorsType() == 2 || newInvestors.getInvestorsType() == 1){
				UserToken userToken = new UserToken();
				userToken.setUserId(userId);
				userToken = userTokenMapper.selectOne(userToken);
				userLevelService.upLevel(userToken.getToken(), 4, null);
			}
			newInvestors.setApprovalStatus(Integer.parseInt(approvalStatus));
			newInvestors.setCreateTime(new Date());
			newInvestors.setPosition(approval.getCompanyDuties());
			newInvestors.setYn(1);
			newInvestors.setApprovalTime(new Date());
			investorsMapper.insert(newInvestors);

			Integer investorId = newInvestors.getId();
			String anli = approval.getInvestorsApprovalcolCase();
			if (anli != null){
				String[] anliArray = anli.split(anli);


				//新建投资人的投资案例
				for (int i=0;i<anliArray.length;i++){
					InvestorInvestmentCase investorInvestmentCase = new InvestorInvestmentCase();
					investorInvestmentCase.setInvestorId(investorId);
					investorInvestmentCase.setInvestmentCase(anliArray[i]);

					investorInvestmentCaseMapper.insert(investorInvestmentCase);
				}
			}

		}



		result.setStatus(200);
		result.setMessage("审核操作成功");
		return result;
	}

	/**
	 * 获取工作名片
	 * @param approvalId 投资审核记录ID
	 * @return
	 */
	@Override
	public CommonDto<String> getWorkcard(String approvalId) {
		CommonDto<String> result = new CommonDto<>();
		if(approvalId == null || "".equals(approvalId)){
			result.setStatus(301);
			result.setMessage("无效参数");
			return result;
		}

		String workcard = "";
		InvestorsApproval investorsApproval = new InvestorsApproval();
		investorsApproval.setId(Integer.parseInt(approvalId));
		investorsApproval = investorsApprovalMapper.selectByPrimaryKey(investorsApproval);
		if(investorsApproval != null){
			workcard = investorsApproval.getWorkCard();
		}else{
			result.setStatus(302);
			result.setMessage("未找到该审核记录");
			return  result;
		}

		result.setStatus(200);
		result.setMessage("查询工作名片成功");
		result.setData(workcard);
		return result;
	}

	@Override
	public CommonDto<String> sendTemplate(Integer userId, Integer status, String formId) {
		CommonDto<String> result = new CommonDto<>();
		UsersWeixin userswx = new UsersWeixin();
		userswx.setUserId(userId);

		String kaitou = "";
		String name = "";
		String xiaoxi = "";
		String type = null;
		if (status == 0 ){
			result.setData(null);
			result.setMessage("传入类型错误");
			result.setStatus(50001);

			return result;
		}

		if (status == 1 || status == 2){
			kaitou = "抱歉！";
		}else {
			kaitou = "恭喜成为";
		}

		switch (status){
			case 1:

				name = "您的投资人认证未通过审核";
				break;
			case 2:name = "您已被取消投资人资格";
				break;
			case 3:name = "个人投资人";
				type = "";
				break;
			case 4:name = "机构投资人";
				type = "";
				break;
			case 5:name = "VIP投资人";
				type = "";
				break;
		}

		xiaoxi = kaitou + name;

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


		Users users = usersMapper.selectByPrimaryKey(userId);
		String desc = users.getActualName() +" " + users.getCompanyName() + " " + users.getCompanyDuties();


		try {
			List<WxMaTemplateMessage.Data> datas = new ArrayList<>();
			try {
				if (status == 1 || status == 2) {//认证失败
					datas.add(new WxMaTemplateMessage.Data("keyword1",name));
//					datas.add(new WxMaTemplateMessage.Data("keyword2",xiaoxi,"#EA4343"));
					this.wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.newBuilder().templateId("RcjdkVcWR9K3Jmfz2HVbMKKLoVHhXEJkpz42Lgr6t6E").formId(formId).toUser(openId).data(datas).build());
				}

				if (status == 3 || status == 4 || status == 5) { //认证成功
					datas.add(new WxMaTemplateMessage.Data("keyword1",name));
					datas.add(new WxMaTemplateMessage.Data("keyword2",xiaoxi,"#EA4343"));
					datas.add(new WxMaTemplateMessage.Data("keyword3",desc));
					this.wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.newBuilder().templateId("IQL59_p78hezrN9Oz6UAStwSyFk8ZbLgVPaPqEi1KyA").formId(formId).toUser(openId).data(datas).page("pages/boot/boot").build());
				}
			} catch (WxErrorException e) {
				e.printStackTrace();
				result.setStatus(50001);
				result.setMessage("该formid已经被使用，或者无效");
				result.setData(null);

				return result;
			}
			//wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.newBuilder().toUser(openId).data().build());
//			wxService.getMsgService().sendKefuMsg(message);
			result.setStatus(200);
			result.setMessage("发送成功");
			result.setData(null);

		}catch (Exception e){
			log.error(e.getMessage(),e);
			result.setData(null);
			result.setMessage("服务器端发生错误");
			result.setStatus(502);

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
	@Override
	public CommonDto<String> specialApproval(Integer userId,Integer status,String userName,String companyName,String comanyDuties){
		CommonDto<String> result = new CommonDto<>();
		Date now = new Date();

		//判断好状态
		Integer approvalStatus = -1;

		switch (status){
			case 1:approvalStatus = 0;
				break;
			case 2:approvalStatus = 0;
				break;
			default:
				approvalStatus = 1;
		}

		Integer approvalType = -1;
		switch (status){
			case 3:approvalType = 0;
				break;
			case 4:approvalType = 1;
				break;
			case 5:approvalType = 2;
				break;
			default:
				approvalType = null;
		}
		//先查用户投资人信息是否存在
		Investors investors = new Investors();
		investors.setUserId(userId);

		Investors investorsForSearch = investorsMapper.selectOne(investors);
		if (investorsForSearch != null){
			//存在的时候获取投资人id更新用户身份
			Integer tzrid = investorsForSearch.getId();
			investors.setYn(1);
			investors.setPosition(comanyDuties);
			investors.setName(userName);
			investors.setApprovalTime(now);
			investors.setApprovalStatus(approvalStatus);
			investors.setInvestorsType(approvalType);

			investorsMapper.updateByPrimaryKeySelective(investors);

			//升级为VIP投资人
			if(investors.getInvestorsType() == 2 || investors.getInvestorsType() == 1){
				UserToken userToken = new UserToken();
				userToken.setUserId(userId);
				userToken = userTokenMapper.selectOne(userToken);
				userLevelService.upLevel(userToken.getToken(), 4, "VIP_Investor");
			}

			//更新用户表的信息
			Users users =new Users();
			users.setActualName(userName);
			users.setCompanyName(companyName);
			users.setCompanyDuties(comanyDuties);
			users.setId(userId);

			usersMapper.updateByPrimaryKeySelective(users);
		}else {
			//不存在的时候新建投资人信息
			Investors investorsForInsert = new Investors();



			investorsForInsert.setUserId(userId);
			investorsForInsert.setApprovalStatus(approvalStatus);
			investorsForInsert.setCreateTime(now);
			investorsForInsert.setInvestorsType(approvalType);
			investorsForInsert.setApprovalTime(now);
			investorsForInsert.setName(userName);
			investorsForInsert.setPosition(comanyDuties);
			investorsForInsert.setYn(1);

			investorsMapper.insert(investorsForInsert);

			//升级为VIP投资人
			if(investorsForInsert.getInvestorsType() == 2 || investorsForInsert.getInvestorsType() == 1){
				UserToken userToken = new UserToken();
				userToken.setUserId(userId);
				userToken = userTokenMapper.selectOne(userToken);
				userLevelService.upLevel(userToken.getToken(), 4, "VIP_Investor");
			}

			//更新用户表的信息
			Users users =new Users();
			users.setActualName(userName);
			users.setCompanyName(companyName);
			users.setCompanyDuties(comanyDuties);
			users.setId(userId);

			usersMapper.updateByPrimaryKeySelective(users);
		}

		result.setData(null);
		result.setStatus(200);
		result.setMessage("success");
		return result;
	}
}
