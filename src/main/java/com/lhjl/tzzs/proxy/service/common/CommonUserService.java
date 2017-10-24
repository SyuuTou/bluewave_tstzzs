package com.lhjl.tzzs.proxy.service.common;

import com.lhjl.tzzs.proxy.mapper.UserTokenMapper;
import com.lhjl.tzzs.proxy.model.UserToken;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 获取用户ID类
 * Created by 蓝海巨浪 on 2017/10/23.
 */
@Component
public class CommonUserService {

    @Resource
    private UserTokenMapper userTokenMapper;

    /**
     * 获取本地用户ID
     * @param tokenStr 用户token
     * @return
     */
    public Integer getLocalUserId(String tokenStr){
        //获取本系统用户ID
        UserToken token = new UserToken();
        token.setToken(tokenStr);
        List<UserToken> users = userTokenMapper.select(token);
        Integer localUserId = null;
        if(users.size() > 0){
            localUserId = users.get(0).getUserId();
        }
        return localUserId;
    }
}
