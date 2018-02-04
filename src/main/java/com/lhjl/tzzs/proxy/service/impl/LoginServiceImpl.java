package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;
import com.lhjl.tzzs.proxy.mapper.AdminUserMapper;
import com.lhjl.tzzs.proxy.mapper.UsersTokenLtsMapper;
import com.lhjl.tzzs.proxy.model.AdminUser;
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
    private UsersTokenLtsMapper usersTokenLtsMapper;

    @Override
    public CommonDto<String> login(LoginReqBody body) {
        CommonDto<String> result = new CommonDto<>();

        if(StringUtils.isAnyBlank(body.getUserName(), body.getPassword())){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData("请输入账号和密码");
        }

        Integer userId = adminUserMapper.selectByLoginBody(body.getUserName(), body.getPassword());

        if(userId == null || "undefined".equals(userId)){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData("没有该用户");
            return result;
        }

        String token = usersTokenLtsMapper.findTokenByUserid(userId);

        if(null != token && !"undefined".equals(token)){
            result.setStatus(200);
            result.setMessage("success");
            result.setData("登录成功");
            return result;
        }
        result.setStatus(300);
        result.setMessage("failed");
        result.setData("登录失败");
        return result;
    }
}
