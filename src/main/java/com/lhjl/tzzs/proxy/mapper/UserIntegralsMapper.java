package com.lhjl.tzzs.proxy.mapper;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.UserIntegrals;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
@Mapper
public interface UserIntegralsMapper extends OwnerMapper<UserIntegrals> {

	BigDecimal findIntegralsZ(@Param("userId") Integer userId);
	BigDecimal findIntegralsX(@Param("userId") Integer userId);
	String findBySkey(@Param("appId") Integer appId, @Param("leId") Integer leId);
	Integer findByQnum (@Param("leId") Integer leId);
	Map<String,Object>findIntegralsU(@Param("appId") Integer appId, @Param("userId") Integer userId);
	
}
