package com.lhjl.tzzs.proxy.dto;

public class ProjectInvestmentDto {
	private String token;
	private String nickName;
	private String headpic;
	private String investmentInstitutionsName;
	private Integer investmentInstitutionId;
	private String actualName;
	private String openId;
	private Integer userid;
	public String getToken() {
		return token;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadpic() {
		return headpic;
	}
	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}
	public String getInvestmentInstitutionsName() {
		return investmentInstitutionsName;
	}
	public void setInvestmentInstitutionsName(String investmentInstitutionsName) {
		this.investmentInstitutionsName = investmentInstitutionsName;
	}
	public Integer getInvestmentInstitutionId() {
		return investmentInstitutionId;
	}
	public void setInvestmentInstitutionId(Integer investmentInstitutionId) {
		this.investmentInstitutionId = investmentInstitutionId;
	}
	public String getActualName() {
		return actualName;
	}
	public void setActualName(String actualName) {
		this.actualName = actualName;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
	
}
