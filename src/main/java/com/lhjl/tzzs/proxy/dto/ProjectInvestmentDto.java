package com.lhjl.tzzs.proxy.dto;

public class ProjectInvestmentDto {
	private String token;
	private String nickName;
	private String headpic;
	private String realName;
	private String openId;
	private Integer userId;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
