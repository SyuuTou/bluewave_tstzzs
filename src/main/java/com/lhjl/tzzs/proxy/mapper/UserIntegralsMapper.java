package com.lhjl.tzzs.proxy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.UserIntegrals;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
@Mapper
public interface UserIntegralsMapper extends OwnerMapper<UserIntegrals> {

	Integer findIntegralsZ(@Param("userId") Integer userId);
	Integer findIntegralsX(@Param("userId") Integer userId);
	String findBySkey(@Param("leId") Integer leId);
	Integer findByQnum (@Param("leId") Integer leId);
	Map<String,Object>findIntegralsU(@Param("userId") Integer userId);
	
}
