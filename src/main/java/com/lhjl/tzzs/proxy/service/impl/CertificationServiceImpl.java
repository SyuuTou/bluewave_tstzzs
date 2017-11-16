package com.lhjl.tzzs.proxy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public CommonDto<List<Map<String, Object>>> findcertification(String investorsName) {
		CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
        List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
		// 查询机构是否存在
		InvestmentInstitutions investmentInstitutions =new InvestmentInstitutions();
		investmentInstitutions.setShortName(investorsName);
		investmentInstitutions =investmentInstitutionsMapper.selectOne(investmentInstitutions);
		if(investmentInstitutions !=null){
			//根据机构的名称查找机构投资人
			String shortName =investmentInstitutions.getShortName();
			List<Map<String, Object>> list1 =investorsApprovalMapper.findApprovalName(shortName);
			if(list1.size()>0){
				for(Map<String, Object> obj :list1){
                   //查找用户头像
					Users users =new Users();
					users.setId(Integer.valueOf(String.valueOf(obj.get("userid"))));
					users =usersMapper.selectOne(users);
					if(users !=null) {
                        if (users.getHeadpicReal() == null) {
                            if (users.getHeadpic() != null) {
                                obj.put("headpicReal", users.getHeadpic());

                            } else {
                                obj.put("headpicReal", "");
                            }
                        } else {
                            obj.put("headpicReal", users.getHeadpicReal());
                        }
                        //查找用户名字
                        if (users.getActualName() != null) {
                            obj.put("actualName()",users.getActualName());

                        } else {
                            obj.put("actualName()","");
                        }
                    }else {
                        result.setStatus(51003);
                        result.setMessage("该用户存在异常");
                    }
                    //查找token
                    UserToken userToken =new UserToken();
					userToken.setUserId(Integer.valueOf(String.valueOf(obj.get("userid"))));
					userToken =userTokenMapper.selectOne(userToken);
					if(userToken.getToken() != null) {
                        obj.put("token", userToken.getToken());
                    }else{
                        result.setStatus(51004);
                        result.setMessage("token不存在");
                    }
                    //nickname查找
                    UsersWeixin usersWeixin =new UsersWeixin();
					usersWeixin.setUserId(Integer.valueOf(String.valueOf(obj.get("userid"))));
                    usersWeixin =usersWeixinMapper.selectOne(usersWeixin);
                    if( usersWeixin.getNickName() != null){
                        obj.put("nickName", usersWeixin.getNickName());
                    }else{
                        obj.put("nickName", "");
                    }
                    //openid 查找
                    if( usersWeixin.getOpenid() != null){
                        obj.put("openid", usersWeixin.getOpenid());
                    }else{
                        obj.put("openid", "");
                    }
                    obj.put("investmentInstitutionsName",investmentInstitutions.getShortName());
                    obj.put("investmentInstitutionsid",investmentInstitutions.getId());
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
