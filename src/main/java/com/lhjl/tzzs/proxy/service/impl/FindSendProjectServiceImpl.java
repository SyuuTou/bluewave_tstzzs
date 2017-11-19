package com.lhjl.tzzs.proxy.service.impl;

import java.util.*;

import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.FindSendProjectService;
@Service
public class FindSendProjectServiceImpl implements FindSendProjectService {


	@Autowired
	private ProjectSendLogsMapper projectSendLogsMapper;

	@Autowired
	private UsersMapper usersMapper;

	@Autowired
	private UsersWeixinMapper usersWeixinMapper;


	@Autowired
	private ProjectFinancingInvestmentInstitutionRelationshipMapper projectFinancingInvestmentInstitutionRelationshipMapper;

	@Autowired
	private InvestmentInstitutionsMapper investmentInstitutionsMapper;


	/**
	 * 查询项目列表
	 *
	 * @param shortName
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public CommonDto<List<Map<String,Object>>> findIntegralsSend(String shortName, Integer pageNum, Integer pageSize) {
		CommonDto<List<Map<String,Object>>>result = new CommonDto<List<Map<String,Object>>>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//最多返回100条记录
        /*if(beginNum > 100){
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        }else{
            pageSize = (100 - beginNum)>=pageSize?pageSize:(100-beginNum);
        }*/
		Integer beginNum = (pageNum - 1) * pageSize;
		//入参为空
		if (shortName == null || "".equals(shortName)) {
			list = projectSendLogsMapper.findSendListAll(beginNum, pageSize);
			for (Map<String,Object> obj : list) {
				Users users = new Users();
				users.setId(Integer.valueOf(String.valueOf(obj.get("userid"))));
				users = usersMapper.selectOne(users);
				//查找姓名
				if (users.getActualName() == null) {
					UsersWeixin usersWeixin = new UsersWeixin();
					usersWeixin.setUserId(Integer.valueOf(String.valueOf(obj.get("userid"))));
					usersWeixin = usersWeixinMapper.selectOne(usersWeixin);
					if (usersWeixin.getNickName() != null) {
						obj.put("relName",usersWeixin.getNickName());
					} else {
						obj.put("relName","");
					}
				} else {
					obj.put("relName",users.getActualName());
				}
				//查找职务
				if (users.getCompanyDuties() != null) {
					obj.put("zhiwu",users.getCompanyDuties());

				} else {
					obj.put("zhiwu","");
				}
				//查找机构名称
				ProjectFinancingInvestmentInstitutionRelationship projectFinancingInvestmentInstitutionRelationship = new ProjectFinancingInvestmentInstitutionRelationship();
				projectFinancingInvestmentInstitutionRelationship.setProjectSendLogId(Integer.valueOf(String.valueOf(obj.get("id"))));
				List<ProjectFinancingInvestmentInstitutionRelationship> logeducation = projectFinancingInvestmentInstitutionRelationshipMapper.select(projectFinancingInvestmentInstitutionRelationship);
				if (logeducation.size() > 0) {
					String[] workArray = {};
					List<String> a = new LinkedList<String>();
					for (ProjectFinancingInvestmentInstitutionRelationship ll : logeducation) {
						//查找机构名字
						InvestmentInstitutions dataLogeducation = new InvestmentInstitutions();
						dataLogeducation.setId(ll.getInvestmentInstitutionId());
						InvestmentInstitutions investmentInstitutions = investmentInstitutionsMapper.selectOne(dataLogeducation);
						workArray = new String[a.size()];
						a.add(investmentInstitutions.getShortName());
						workArray = a.toArray(workArray);
						obj.put("investmentName",workArray);

					}
				} else {
					String[] workArray1 = {};
					obj.put("investmentName",workArray1);
				}

				//审核状态
				/*if(String.valueOf(obj.get("check_status")) ==null){
					obj.put("check",0);
				}else{
					obj.put("check",Integer.valueOf(String.valueOf(obj.get("check_status"))));
				}*/
				//项目名称
				/*obj.setCompanyShortName(obj.getCompanyShortName());
				//一句话介绍
				obj.setCompanyOneSentenceIntroduct(obj.getCompanyOneSentenceIntroduct());*/


			}


		} else {
			list = projectSendLogsMapper.findSendList(shortName,beginNum, pageSize);
			for (Map<String,Object> obj : list) {
				Users users = new Users();
				users.setId(Integer.valueOf(String.valueOf(obj.get("userid"))));
				users = usersMapper.selectOne(users);
				//查找姓名
				if (users.getActualName() == null) {
					UsersWeixin usersWeixin = new UsersWeixin();
					usersWeixin.setUserId(Integer.valueOf(String.valueOf(obj.get("userid"))));
					usersWeixin = usersWeixinMapper.selectOne(usersWeixin);
					if (usersWeixin.getNickName() != null) {
						obj.put("relName",usersWeixin.getNickName());
					} else {
						obj.put("relName","");
					}
				} else {
					obj.put("relName",users.getActualName());
				}
				//查找职务
				if (users.getCompanyDuties() != null) {
					obj.put("zhiwu",users.getCompanyDuties());

				} else {
					obj.put("zhiwu","");
				}
				//查找机构名称
				ProjectFinancingInvestmentInstitutionRelationship projectFinancingInvestmentInstitutionRelationship = new ProjectFinancingInvestmentInstitutionRelationship();
				projectFinancingInvestmentInstitutionRelationship.setProjectSendLogId(Integer.valueOf(String.valueOf(obj.get("id"))));
				List<ProjectFinancingInvestmentInstitutionRelationship> logeducation = projectFinancingInvestmentInstitutionRelationshipMapper.select(projectFinancingInvestmentInstitutionRelationship);
				if (logeducation.size() > 0) {
					String[] workArray = {};
					List<String> a = new LinkedList<String>();
					for (ProjectFinancingInvestmentInstitutionRelationship ll : logeducation) {
						//查找机构名字
						InvestmentInstitutions dataLogeducation = new InvestmentInstitutions();
						dataLogeducation.setId(ll.getInvestmentInstitutionId());
						InvestmentInstitutions investmentInstitutions = investmentInstitutionsMapper.selectOne(dataLogeducation);
						workArray = new String[a.size()];
						a.add(investmentInstitutions.getShortName());
						workArray = a.toArray(workArray);
						obj.put("investmentName",workArray);

					}
				} else {
					String[] workArray1 = {};
					obj.put("investmentName",workArray1);
				}

				//审核状态
				/*if(String.valueOf(obj.get("check_status")) ==null){
					obj.put("check",0);
				}else{
					obj.put("check",Integer.valueOf(String.valueOf(obj.get("check_status"))));
				}*/
				//项目名称
				/*obj.setCompanyShortName(obj.getCompanyShortName());
				//一句话介绍
				obj.setCompanyOneSentenceIntroduct(obj.getCompanyOneSentenceIntroduct());*/


			}

		}
        result.setData(list);
		return result;
	}
}
