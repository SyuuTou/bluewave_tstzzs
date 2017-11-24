package com.lhjl.tzzs.proxy.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TouZiDto;
import com.lhjl.tzzs.proxy.dto.TouZiNewDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.InvestorsApproval;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.InvestorsApprovalNewService;
import com.lhjl.tzzs.proxy.service.InvestorsApprovalService;
import com.lhjl.tzzs.proxy.service.UserLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lmy on 2017/11/24.
 */
@Service
public class InvestorsApprovalNewServiceImpl implements InvestorsApprovalNewService {


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
		public CommonDto<String> saveTouZiNew(TouZiNewDto params) {
			CommonDto<String> result =new CommonDto<String>();
			try {
				//先取出参数进行验证
				String token = params.getToken();
				String compellation = params.getCompellation();
				String dataName = params.getDateName();
				String company = params.getOrganization();
				String companyDuties = params.getFillOffice();
				String formId = params.getFormId();
				Integer ivesId=params.getInvesId();

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

				if ("".equals(ivesId) || ivesId == null || "undefined".equals(ivesId)){
					result.setData(null);
					result.setMessage("ivesId不存在");
					result.setStatus(50001);

					log.info("ivesId不存在");

					return result;
				}


				InvestorsApproval investorsApproval =new  InvestorsApproval();
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
                investorsApproval.setInstitutionId(ivesId);
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
}
