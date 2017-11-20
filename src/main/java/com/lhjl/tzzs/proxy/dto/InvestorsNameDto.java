package com.lhjl.tzzs.proxy.dto;

/**
 * Created by zyy on 2017/11/16.
 */
public class InvestorsNameDto {
	/**
	 * 机构简称
	 */
	private String investorsName;
	/**
	 * 机构id
	 */
	private Integer investorInstitutionId;

	public Integer getInvestorInstitutionId() {
		return investorInstitutionId;
	}

	public void setInvestorInstitutionId(Integer investorInstitutionId) {
		this.investorInstitutionId = investorInstitutionId;
	}


	public String getInvestorsName() {
		return investorsName;
	}

	public void setInvestorsName(String investorsName) {
		this.investorsName = investorsName;
	}
    
    
}
