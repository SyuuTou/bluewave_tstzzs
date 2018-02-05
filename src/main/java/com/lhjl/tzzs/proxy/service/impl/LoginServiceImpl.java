package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;
import com.lhjl.tzzs.proxy.mapper.AdminUserMapper;
import com.lhjl.tzzs.proxy.mapper.UserTokenMapper;
import com.lhjl.tzzs.proxy.mapper.UsersTokenLtsMapper;
import com.lhjl.tzzs.proxy.model.AdminUser;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.model.UsersTokenLts;
import com.lhjl.tzzs.proxy.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by lanhaijulang on 2018/2/4.
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public CommonDto<AdminUser> login(LoginReqBody body,Integer appid) {
        CommonDto<AdminUser> result = new CommonDto<>();

        if(StringUtils.isAnyBlank(body.getUserName(), body.getPassword())){
            result.setStatus(300);
            result.setMessage("请输入账号和密码");
            result.setData(null);
        }
        AdminUser adminUser=new AdminUser();
        adminUser.setName(body.getUserName());
        adminUser.setPassword(body.getPassword());
        adminUser.setMetaAppId(appid);
        adminUser = adminUserMapper.selectOne(adminUser);
        if(adminUser == null ){
            result.setStatus(300);
            result.setMessage("没有该用户");
            result.setData(null);
            return result;
        }
    	UserToken userToken =new UserToken();
        userToken.setUserId(adminUser.getUserId());
        
        try {
        	userToken = userTokenMapper.selectOne(userToken);
        }catch(Exception e) {
        	result.setStatus(500);
            result.setMessage("user_token表中存在两条相同的userId");
            result.setData(null); 
            return result;
        }
        //根据用户是否存在token判断是否登录成功
        if(userToken != null) {
        	if( userToken.getToken() != null && !("".equals(userToken.getToken())) ){
        		adminUser.setToken(userToken.getToken());
                result.setStatus(200);
                result.setMessage("登录成功");
                result.setData(adminUser);
                return result;
            }
        }
        
        result.setStatus(300);
        result.setMessage("登录失败");
        result.setData(null);
        return result;
    }
}
