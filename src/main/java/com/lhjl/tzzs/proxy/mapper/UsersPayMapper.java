package com.lhjl.tzzs.proxy.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.UsersPay;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface UsersPayMapper extends OwnerMapper<UsersPay> {
	/**
	 * 累计付费总金额
	 * @return
	 */
	BigDecimal getSumPayNum(@Param("userId") Integer UserId);
	/**
	 * 累计金币充值金额
	 * @return
	 */
	BigDecimal getSumIntegrateCostNum(@Param("userId") Integer userId);
	/**
	 * 实缴会员费金额
	 * @return
	 */
	BigDecimal getActualVipCostNum(@Param("userId") Integer userId);
}