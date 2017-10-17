package com.lhjl.tzzs.proxy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
@Mapper
public interface UsersMapper extends OwnerMapper<Users> {
	  Integer findByUuid(@Param("uuids") String uuids);
	  Integer findByUserid(@Param("userId") Integer userId);
	  Float findByBei(@Param("leId") Integer leId);
	  Integer findByJinE(@Param("skey") String skey);
	 
}