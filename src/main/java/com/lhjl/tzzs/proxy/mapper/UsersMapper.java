package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectAdministratorOutputDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper extends OwnerMapper<Users> {
	Integer findByUuid(@Param("uuids") String uuids);
	
	//根据用户Id获取用户信息
	Users findUserById(@Param("userId") Integer userId);
	//获取分页数据
	List<Users> findSplit(Map<String,Integer> map);
	
	Integer findByUserid(@Param("userId") Integer userId);
	Float findByBei(@Param("leId") Integer leId);
	Integer findByJinE(@Param("skey") String skey);
	String findByUserLevel(@Param("leId") Integer leId);
	List<Map<String,Object>> findUserList(@Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize);
	Map<String,String> findUserInfoAssemble(@Param("userId") Integer userId);

	/**
	 * 查一个用户的复合信息的mapper
	 * @param token
	 * @return
	 */
	Map<String,String> findUserComplexInfoOne(@Param("token") String token);

}
