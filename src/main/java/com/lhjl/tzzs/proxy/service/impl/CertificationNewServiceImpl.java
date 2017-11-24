package com.lhjl.tzzs.proxy.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectInvestmentDto;
import com.lhjl.tzzs.proxy.service.CertificationNewService;
import org.springframework.stereotype.Service;

/**
 * Created by lmy on 2017/11/24.
 */
@Service
public class CertificationNewServiceImpl implements CertificationNewService {

		 private static final org.slf4j.Logger log = LoggerFactory.getLogger(CertificationServiceImpl.class);
		    
			@Autowired
			private UsersMapper usersMapper;

			@Autowired
			private UserTokenMapper userTokenMapper;

			@Autowired
			private UsersWeixinMapper usersWeixinMapper;

			@Autowired
			private InvestorsApprovalMapper investorsApprovalMapper;

			@Autowired
			private InvestmentInstitutionsMapper investmentInstitutionsMapper;
            @Autowired
            private InvestorsMapper investorsMapper;

			@Override
			public CommonDto<List<ProjectInvestmentDto>>findcertificationNew(Integer investorInstitutionId) {
		        CommonDto<List<ProjectInvestmentDto>> result = new CommonDto<List<ProjectInvestmentDto>>();
		        List<ProjectInvestmentDto> list =new LinkedList<ProjectInvestmentDto>();
				// 查询机构是否存在
				InvestmentInstitutions investmentInstitutions =new InvestmentInstitutions();
				investmentInstitutions.setId(investorInstitutionId);

				investmentInstitutions =investmentInstitutionsMapper.selectOne(investmentInstitutions);
				if(investmentInstitutions !=null){
					//根据机构的名称查找机构投资人
					/*String shortName =investmentInstitutions.getShortName();
		            List<ProjectInvestmentDto> list1 =investorsApprovalMapper.findApprovalName(shortName);*/
					Integer invesId=investmentInstitutions.getId();
                    List<ProjectInvestmentDto> list1 =investorsMapper.findinvestorId(invesId);
					if(list1.size()>0){
						for(ProjectInvestmentDto obj :list1){
		                   //查找用户头像
							Users users =new Users();
							users.setId(obj.getUserId());
							users =usersMapper.selectOne(users);
							if(users !=null) {
		                        if (users.getHeadpicReal() == null) {
		                            if (users.getHeadpic() != null) {
                                        obj.setHeadpic( users.getHeadpic());

		                            } else {
                                        obj.setHeadpic("");
		                            }
		                        } else {
                                    obj.setHeadpic(users.getHeadpicReal());
		                        }
		                        //查找用户名字
		                        if (users.getActualName() != null) {
                                    obj.setRealName(users.getActualName());

		                        } else {
                                    obj.setHeadpic("");
		                        }
		                    }else {
		                        result.setStatus(51003);
		                        result.setMessage("当前用户已失效");

		                        Integer userid = obj.getUserId();
		                        log.info("获取机构投资人场景");
		                        log.info("当前用户已失效,对应的用户id为：{}",userid);


		                        return result;
		                    }
		                    //查找token
		                    UserToken userToken =new UserToken();
							userToken.setUserId(obj.getUserId());
							userToken =userTokenMapper.selectOne(userToken);
							if (userToken != null){
		                        if(userToken.getToken() != null) {
                                    obj.setToken( userToken.getToken());
		                        }else{
		                            result.setStatus(51004);
		                            result.setMessage("token不存在,可能被人工清理数据库了");


		                            Integer userid = obj.getUserId();
		                            log.info("获取机构投资人场景");
		                            log.info("当前用户token已失效,可能被人工清理数据库了,对应的用户id为：{}",userid);

		                            return result;
		                        }
		                    }else {
                                obj.setToken("");
		                    }

		                    //nickname查找
		                    UsersWeixin usersWeixin =new UsersWeixin();
							usersWeixin.setUserId(obj.getUserId());
		                    usersWeixin =usersWeixinMapper.selectOne(usersWeixin);
		                    if (usersWeixin != null){
		                        if( usersWeixin.getNickName() != null){
                                    obj.setNickName( usersWeixin.getNickName());
		                        }else{
                                    obj.setNickName("");
		                        }
		                        //openid 查找
		                        if( usersWeixin.getOpenid() != null){
                                    obj.setOpenId(usersWeixin.getOpenid());
		                        }else{
                                    obj.setOpenId("");
		                        }
		                    }else {
                                obj.setNickName("");
                                obj.setOpenId("");
		                    }

		                    if (usersWeixin != null){
		                        list.add(obj);
		                    }
						}
					}else {
		                result.setStatus(200);
		                result.setMessage("当前机构还没有投资人入驻");
		                result.setData(list);

		                log.info("获取机构投资人场景");
		                log.info("当前机构还没有投资人入驻");

		                return result;
		            }
				}else{
					result.setStatus(51001);
					result.setMessage("没有找到当前id对应的机构，请检查");

		            log.info("获取机构投资人场景");
		            log.info("没有找到当前id对应的机构，请检查");

					return result;
				}
		        result.setData(list);
				return result;
			}
			
		}
