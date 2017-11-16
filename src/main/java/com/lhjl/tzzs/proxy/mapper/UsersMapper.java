package com.lhjl.tzzs.proxy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper extends OwnerMapper<Users> {
	Integer findByUuid(@Param("uuids") String uuids);
	Integer findByUserid(@Param("userId") Integer userId);
	Float findByBei(@Param("leId") Integer leId);
	Integer findByJinE(@Param("skey") String skey);
	String findByUserLevel(@Param("leId") Integer leId);
	List<Map<String,Object>> findUserList(@Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize);
	Map<String,String> findUserInfoAssemble(@Param("userId") Integer userId);

}
