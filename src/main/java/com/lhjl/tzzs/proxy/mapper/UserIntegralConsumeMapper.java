package com.lhjl.tzzs.proxy.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.UserIntegralConsume;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface UserIntegralConsumeMapper extends OwnerMapper<UserIntegralConsume> {
	/**
	 * 剩余金币数量
	 * @param UserId
	 * @return
	 */
	Integer getCostNum(@Param("userId") Integer UserId);
}