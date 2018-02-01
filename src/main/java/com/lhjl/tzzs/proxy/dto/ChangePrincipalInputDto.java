package com.lhjl.tzzs.proxy.dto;

import java.util.List;

public class ChangePrincipalInputDto {
	/**
	 * 投资人的用户id组成的list
	 */
	private List<Integer> investorIds;
	/**
	 * 负责人
	 */
	private String irPrincipal;
	
	public List<Integer> getInvestorIds() {
		return investorIds;
	}
	public void setInvestorIds(List<Integer> investorIds) {
		this.investorIds = investorIds;
	}
	
	public String getIrPrincipal() {
		return irPrincipal;
	}
	public void setIrPrincipal(String irPrincipal) {
		this.irPrincipal = irPrincipal;
	}
	@Override
	public String toString() {
		return "ChangePrincipalInputDto [investorIds=" + investorIds + ", irPrincipal=" + irPrincipal + "]";
	}
	
	
	
}
