package com.lhjl.tzzs.proxy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TouZiDto;
import com.lhjl.tzzs.proxy.mapper.InvestorsApprovalMapper;
import com.lhjl.tzzs.proxy.mapper.UserTokenMapper;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.model.InvestorsApproval;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.InvestorsApprovalService;

import net.sf.jsqlparser.expression.StringValue;
@Service
public class InvestorsApprovalserviceImpl implements InvestorsApprovalService {
	@Resource
	private InvestorsApprovalMapper investorsApprovalMapper;
	
	@Resource
	private UserTokenMapper userTokenMapper;
	@Resource
	private UsersMapper usersMapper;
    
	/**
	 * 认证投资人信息记录
	 */
	@Transactional
	@Override
	public CommonDto<String> saveTouZi(TouZiDto params) {
		CommonDto<String> result =new CommonDto<String>();
		 InvestorsApproval  investorsApproval =new  InvestorsApproval();
		 UserToken userToken =new UserToken();
		 userToken.setToken(params.getToken());
	     userToken =userTokenMapper.selectOne(userToken);
		 investorsApproval.setUserid(userToken.getUserId());
		 investorsApproval.setApprovalUsername(params.getCompellation());
		 if("个人投资人".equals(params.getDateName())){
		 investorsApproval.setInvestorsType(0);
		 }
		 if("机构投资人".equals(params.getDateName())){
			 investorsApproval.setInvestorsType(1);
			 }
		 if("VIP投资人".equals(params.getDateName())){
			 investorsApproval.setInvestorsType(2);
			 }
		 investorsApproval.setDescription(params.getEvaContent());
		 investorsApproval.setCompany(params.getOrganization());
		 investorsApproval.setCompanyDuties(params.getFillOffice());
		 investorsApproval.setWorkCard(params.getTempFilePaths());
		 investorsApproval.setApprovalResult(0);
		 investorsApproval.setReviewTime(new Date());
		 investorsApproval.setCreateTime(new Date());
		 investorsApprovalMapper.insert(investorsApproval);
		return result;
	}
  /**
   * 数据的回显
   */
	@Override
	public CommonDto<Map<String,Object>> findInvestorsApproval(String token) {
		CommonDto<Map<String, Object>> result =new CommonDto<Map<String, Object>> ();
		Map<String,Object> map =new HashMap<>();
		UserToken userToken =new UserToken();
		 userToken.setToken(token);
		 userToken = userTokenMapper.selectOne(userToken);
		 Integer userId = userToken.getUserId();
		 map=investorsApprovalMapper.findInvestorsApproval(userId);
		 if(map !=null){

			map.put("id", token);
			map.put("renzhengtouzirenshenhebiao7additional","");//其他说明
			map.put("renzhengtouzirenshenhebiao7applicantn",String.valueOf(map.get("approval_username")));
			map.put("renzhengtouzirenshenhebiao7assumeoffi",String.valueOf(map.get("company_duties")));
			map.put("renzhengtouzirenshenhebiao7certificat",String.valueOf(map.get("company")));
			map.put("renzhengtouzirenshenhebiao7frontofbus",String.valueOf(map.get("work_card")));
			map.put("renzhengtouzirenshenhebiao7wherecompa",String.valueOf(map.get("description")));
			Map<String,Object> renzhenleixin =new HashMap<>();
			if(Integer.valueOf(String.valueOf(map.get("investors_type"))) == 0){
				renzhenleixin.put("0",true);
				renzhenleixin.put("1",false);
				renzhenleixin.put("2",false);
			}
			if(Integer.valueOf(String.valueOf(map.get("investors_type"))) == 1){
				renzhenleixin.put("0",false);
				renzhenleixin.put("1",true);
				renzhenleixin.put("2",false);
			}
			if(Integer.valueOf(String.valueOf(map.get("investors_type"))) == 2){
				renzhenleixin.put("0",false);
				renzhenleixin.put("1",false);
				renzhenleixin.put("2",true);
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
				map.put("renzhengtouzirenshenhebiao7assumeoffi",null);
				map.put("renzhengtouzirenshenhebiao7certificat",null);
				map.put("renzhengtouzirenshenhebiao7frontofbus",null);
				map.put("renzhengtouzirenshenhebiao7wherecompa",null);
				Map<String,Object> renzhenleixin =new HashMap<>();
				renzhenleixin.put("0",false);
				renzhenleixin.put("1",false);
				renzhenleixin.put("2",false);
				map.put("renzhenleixin",renzhenleixin);
		 }
		result.setData(map);
		return result;
	}
	/**
	 * 进入审核状态的
	 */

	@Override
	public CommonDto<Map<String, Object>> findInvestorsExamine(String token) {
		CommonDto<Map<String, Object>> result =new CommonDto<Map<String, Object>> ();
		Map<String,Object> map =new HashMap<>();
		UserToken userToken =new UserToken();
		 userToken.setToken(token);
		 userToken = userTokenMapper.selectOne(userToken);
		 Integer userId = userToken.getUserId();
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


}
