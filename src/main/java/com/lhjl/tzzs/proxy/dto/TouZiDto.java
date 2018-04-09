package com.lhjl.tzzs.proxy.dto;

import javax.management.loading.PrivateClassLoader;

public class TouZiDto {
	
	/**
	 * 认证类型
	 */
	private String dateName;  
	/**
	 * 真实姓名
	 */
	private String compellation; 
	/**
	 * 所在公司
	 */
	private String organization;  
	/**
	 * 担任职务
	 */
	private String fillOffice; 
	
	/**
	 * 投资人投资案例
	 */
	private String investorsApprovalcolCase;
	 
	/**
	 * 工作名片
	 */
	private String tempFilePaths;
	
	/**
	 * 认证说明
	 */
	private String evaContent;  
	 
	/**
	 * 认证id
	 */
	private String token;  
	
	private String formId;

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

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
