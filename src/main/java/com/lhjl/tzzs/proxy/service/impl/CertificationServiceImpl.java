package com.lhjl.tzzs.proxy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.ProjectInvestmentDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.model.UsersWeixin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.CertificationService;
@Service
public class CertificationServiceImpl implements CertificationService {
    
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


	@Override
	public CommonDto<List<ProjectInvestmentDto>>findcertification(String investorsName) {
        CommonDto<List<ProjectInvestmentDto>> result = new CommonDto<List<ProjectInvestmentDto>>();
        List<ProjectInvestmentDto> list =new ArrayList<ProjectInvestmentDto>();
		// 查询机构是否存在
		InvestmentInstitutions investmentInstitutions =new InvestmentInstitutions();
		investmentInstitutions.setShortName(investorsName);
		investmentInstitutions =investmentInstitutionsMapper.selectOne(investmentInstitutions);
		if(investmentInstitutions !=null){
			//根据机构的名称查找机构投资人
			String shortName =investmentInstitutions.getShortName();
            List<ProjectInvestmentDto> list1 =investorsApprovalMapper.findApprovalName(shortName);
			if(list1.size()>0){
				for(ProjectInvestmentDto obj :list1){
                   //查找用户头像
					Users users =new Users();
					users.setId(obj.getUserid());
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
                            obj.setActualName(users.getActualName());

                        } else {
                            obj.setHeadpic("");
                        }
                    }else {
                        result.setStatus(51003);
                        result.setMessage("该用户存在异常");
                    }
                    //查找token
                    UserToken userToken =new UserToken();
					userToken.setUserId(obj.getUserid());
					userToken =userTokenMapper.selectOne(userToken);
					if(userToken.getToken() != null) {
                        obj.setToken( userToken.getToken());
                    }else{
                        result.setStatus(51004);
                        result.setMessage("token不存在");
                    }
                    //nickname查找
                    UsersWeixin usersWeixin =new UsersWeixin();
					usersWeixin.setUserId(obj.getUserid());
                    usersWeixin =usersWeixinMapper.selectOne(usersWeixin);
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
                    obj.setInvestmentInstitutionsName(investmentInstitutions.getShortName());
                    obj.setInvestmentInstitutionId(investmentInstitutions.getId());
				}
			}else {
                result.setStatus(51002);
                result.setMessage("该机构还无用户");
            }
            list.addAll(list1);
		}else{
			result.setStatus(51001);
			result.setMessage("查询不到机构的名称");
		}
       result.setData(list);
		return result;
	}
	
}
