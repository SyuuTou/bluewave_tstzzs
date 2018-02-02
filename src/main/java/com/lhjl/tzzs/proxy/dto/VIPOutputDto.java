package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;

import com.lhjl.tzzs.proxy.model.UserLevelRelation;

public class VIPOutputDto {
	/**
	 * 用户的会员信息记录
	 */
	private UserLevelRelation userLevelRelation;
	
	/**
	 * 剩余金币数量
	 */
	private Integer costNum;
	/**
	 * 实缴会员费金额
	 */
	private BigDecimal actualVipCostNum;
	/**
	 * 累计金币充值金额
	 */
	private BigDecimal sumIntegrateCostNum;
	/**
	 * 累计付费总金额
	 * 备注：实缴会员费+金币充值+购买精选服务+其他
	 */
	private BigDecimal sumPayNum;
	
	public UserLevelRelation getUserLevelRelation() {
		return userLevelRelation;
	}

	public void setUserLevelRelation(UserLevelRelation userLevelRelation) {
		this.userLevelRelation = userLevelRelation;
	}
	

	public Integer getCostNum() {
		return costNum;
	}

	public void setCostNum(Integer costNum) {
		this.costNum = costNum;
	}

	public BigDecimal getActualVipCostNum() {
		return actualVipCostNum;
	}

	public void setActualVipCostNum(BigDecimal actualVipCostNum) {
		this.actualVipCostNum = actualVipCostNum;
	}

	public BigDecimal getSumIntegrateCostNum() {
		return sumIntegrateCostNum;
	}

	public void setSumIntegrateCostNum(BigDecimal sumIntegrateCostNum) {
		this.sumIntegrateCostNum = sumIntegrateCostNum;
	}

	public BigDecimal getSumPayNum() {
		return sumPayNum;
	}

	public void setSumPayNum(BigDecimal sumPayNum) {
		this.sumPayNum = sumPayNum;
	}

	@Override
	public String toString() {
		return "VIPOutputDto [userLevelRelation=" + userLevelRelation + ", costNum=" + costNum + ", actualVipCostNum="
				+ actualVipCostNum + ", sumIntegrateCostNum=" + sumIntegrateCostNum + ", sumPayNum=" + sumPayNum + "]";
	}

	
}
