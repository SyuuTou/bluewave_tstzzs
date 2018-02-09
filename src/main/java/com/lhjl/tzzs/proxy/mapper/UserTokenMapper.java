package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

public interface UserTokenMapper extends OwnerMapper<UserToken> {

    UserToken selectUserTokenByToken(@Param("token") String token);
}