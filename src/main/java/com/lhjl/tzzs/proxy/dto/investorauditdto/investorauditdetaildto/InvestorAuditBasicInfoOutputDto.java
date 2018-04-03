package com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditdetaildto;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/2/7.
 */
public class InvestorAuditBasicInfoOutputDto {

    /**
     * 投资人Id
     */
    private Integer investorId;

    /**
     * 身份类型
     */
    private String identityType;

    /**
     * 领域id
     */
    private List<String> segmentations;

    /**
     * 微信
     */
    private String weiChat;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所在城市
     */
    private List<String> citys;

    /**
     * 自定义城市
     */
    private List<String> selfDefCity;

    /**
     * 出生年月
     */
    private String birthDay;

    /**
     * 性别；0：男;1:女
     */
    private Integer sex;

    /**'
     * 学历
     */
    private String diploma;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 任职时间
     */
    private String tenureTime;

    /**
     * 公司简介
     */
    private String companyIntro;

    /**
     * 工作经历
     */
    private List<String> workExperiences;

    /**
     * 教育经历
     */
    private List<String> educationExperiences;

    /**
     * 工作名片
     */
    private String businessCard;

    private String businessCardOposite;

    /**
     * 图片
     */
    private String picture;

    /**
     * 创业经历
     */
    private List<String> businesses;

    /**
     * 创业描述
     */
    private String bussiness;

    /**
     * 教育经历描述
     */
    private String educationExperience;

    /**
     * 工作经历描述
     */
    private String workExperience;

    /**
     * 社会荣誉及资质
     */
    private String honor;

	public Integer getInvestorId() {
		return investorId;
	}

	public void setInvestorId(Integer investorId) {
		this.investorId = investorId;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public List<String> getSegmentations() {
		return segmentations;
	}

	public void setSegmentations(List<String> segmentations) {
		this.segmentations = segmentations;
	}

	public String getWeiChat() {
		return weiChat;
	}

	public void setWeiChat(String weiChat) {
		this.weiChat = weiChat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getCitys() {
		return citys;
	}

	public void setCitys(List<String> citys) {
		this.citys = citys;
	}

	public List<String> getSelfDefCity() {
		return selfDefCity;
	}

	public void setSelfDefCity(List<String> selfDefCity) {
		this.selfDefCity = selfDefCity;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getTenureTime() {
		return tenureTime;
	}

	public void setTenureTime(String tenureTime) {
		this.tenureTime = tenureTime;
	}

	public String getCompanyIntro() {
		return companyIntro;
	}

	public void setCompanyIntro(String companyIntro) {
		this.companyIntro = companyIntro;
	}

	public List<String> getWorkExperiences() {
		return workExperiences;
	}

	public void setWorkExperiences(List<String> workExperiences) {
		this.workExperiences = workExperiences;
	}

	public List<String> getEducationExperiences() {
		return educationExperiences;
	}

	public void setEducationExperiences(List<String> educationExperiences) {
		this.educationExperiences = educationExperiences;
	}

	public String getBusinessCard() {
		return businessCard;
	}

	public void setBusinessCard(String businessCard) {
		this.businessCard = businessCard;
	}

	public String getBusinessCardOposite() {
		return businessCardOposite;
	}

	public void setBusinessCardOposite(String businessCardOposite) {
		this.businessCardOposite = businessCardOposite;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<String> getBusinesses() {
		return businesses;
	}

	public void setBusinesses(List<String> businesses) {
		this.businesses = businesses;
	}

	public String getBussiness() {
		return bussiness;
	}

	public void setBussiness(String bussiness) {
		this.bussiness = bussiness;
	}

	public String getEducationExperience() {
		return educationExperience;
	}

	public void setEducationExperience(String educationExperience) {
		this.educationExperience = educationExperience;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

}
