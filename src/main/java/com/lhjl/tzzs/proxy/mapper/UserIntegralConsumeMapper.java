package com.lhjl.tzzs.proxy.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.UserIntegralConsume;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface UserIntegralConsumeMapper extends OwnerMapper<UserIntegralConsume> {
	/**
	 * 剩余金币数量
	 * @param UserId
	 * @return
	 */
	Integer getCostNum(@Param("userId") Integer UserId);

	@Select("select sum(cost_num) from  user_integral_consume where user_id = #{userId}  and app_id= #{appId} and currency = #{currency}")
	@ResultType(BigDecimal.class)
    BigDecimal selectSumIntegralConsume(@Param("appId") Integer appId, @Param("userId") Integer userId, @Param("currency") Integer currency);
}