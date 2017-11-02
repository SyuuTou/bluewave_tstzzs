package com.lhjl.tzzs.proxy.dto;

import javax.management.loading.PrivateClassLoader;

public class TouZiDto {

	private String dateName;  //投资
	private String compellation; // 姓名
	private String organization;  // 所在公司
	private String fillOffice;  //担任职务
	private String evaContent;  //说明
	private String tempFilePaths; //图片
	private String token;  //认证id
	private String investorsApprovalcolCase;//投资人投资案例


	public String getInvestorsApprovalcolCase() {
		return investorsApprovalcolCase;
	}

	public void setInvestorsApprovalcolCase(String investorsApprovalcolCase) {
		this.investorsApprovalcolCase = investorsApprovalcolCase;
	}

	public String getDateName() {
		return dateName;
	}
	public void setDateName(String dateName) {
		this.dateName = dateName;
	}
	public String getCompellation() {
		return compellation;
	}
	public void setCompellation(String compellation) {
		this.compellation = compellation;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getFillOffice() {
		return fillOffice;
	}
	public void setFillOffice(String fillOffice) {
		this.fillOffice = fillOffice;
	}
	public String getEvaContent() {
		return evaContent;
	}
	public void setEvaContent(String evaContent) {
		this.evaContent = evaContent;
	}
	public String getTempFilePaths() {
		return tempFilePaths;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setTempFilePaths(String tempFilePaths) {
		this.tempFilePaths = tempFilePaths;
	}
	
	

}
