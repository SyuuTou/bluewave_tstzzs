package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

public class ProjectSendTeamBDto {

    /**
     * 提交项目成员id
     * 主键id
     */
    private Integer id;
    
    /**提交项目id*/
    private Integer projectSendBId;

    /**成员姓名*/
    private String memberName;

    /**职务*/
    private String companyDuties;

    /**股份占比*/
    private BigDecimal stockRatio;

    /**成员简介*/
    private String memberDesc;
    
    /**工作经历*/
    private List<String> workExperience;

    /**教育经历*/
    private List<String> educationExperience;
    
    //团队成员审核后期新增字段
    
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 0 离职
     * 1 在职
     * null 无
     */
    private Integer isOnJob;
    /**
     * 团队成员权重权重
     */
    private Integer weight;
    /**
     * 头像
     */
    private String headPicture;

    /**
     * 高清照片
     */
    private String picture;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信
     */
    private String weichat;

    /**
     * 是否隐藏，0:隐藏;1:不隐藏
     */
    private Integer isHide;
    /**
     * 创业经历描述
     */
    private String businessExperienceDesc;

    /**
     * 工作经历描述
     */
    private String workExperienceDesc;

    /**
     * 教育经历描述
     */
    private String educationExperienceDesc;

    /**
     * 所属团队ID
     */
    private Integer teamId;
    /**
     * 自定义团队
     */
    private List<String> selfDefTeam;

    /**
     * 出生日期（曹传桂）
     */
    private String birthDay;

    /**
     * 任职时间（曹传桂）
     */
    private String tenureTime;

    /**
     * 性别:0男1女（曹传桂）
     */
    private Integer sex;
    /**
     * 学历ID(曹传桂)
     */
    private Integer diploma;

    /**
     * 国籍(曹传桂)
     */
    private Integer nationality;
    
    /**
     * 关注领域
     */
    private List<Integer> segmentaionIds;
    /**
     * 投资阶段
     */
    private List<Integer> investStages;
    /**
     * 所在城市
     */
    private List<String> citys;
    
    private List<String> selfDefCitys;
    /**
     * 创业经历
     */
    private List<String> businesses;
    //以上为新增字段
    
    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIsOnJob() {
		return isOnJob;
	}

	public void setIsOnJob(Integer isOnJob) {
		this.isOnJob = isOnJob;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getHeadPicture() {
		return headPicture;
	}

	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeichat() {
		return weichat;
	}

	public void setWeichat(String weichat) {
		this.weichat = weichat;
	}

	public Integer getIsHide() {
		return isHide;
	}

	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}

	public String getBusinessExperienceDesc() {
		return businessExperienceDesc;
	}

	public void setBusinessExperienceDesc(String businessExperienceDesc) {
		this.businessExperienceDesc = businessExperienceDesc;
	}

	public String getWorkExperienceDesc() {
		return workExperienceDesc;
	}

	public void setWorkExperienceDesc(String workExperienceDesc) {
		this.workExperienceDesc = workExperienceDesc;
	}

	public String getEducationExperienceDesc() {
		return educationExperienceDesc;
	}

	public void setEducationExperienceDesc(String educationExperienceDesc) {
		this.educationExperienceDesc = educationExperienceDesc;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public List<String> getSelfDefTeam() {
		return selfDefTeam;
	}

	public void setSelfDefTeam(List<String> selfDefTeam) {
		this.selfDefTeam = selfDefTeam;
	}


	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getTenureTime() {
		return tenureTime;
	}

	public void setTenureTime(String tenureTime) {
		this.tenureTime = tenureTime;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getDiploma() {
		return diploma;
	}

	public void setDiploma(Integer diploma) {
		this.diploma = diploma;
	}

	public Integer getNationality() {
		return nationality;
	}

	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}

	public List<Integer> getSegmentaionIds() {
		return segmentaionIds;
	}

	public void setSegmentaionIds(List<Integer> segmentaionIds) {
		this.segmentaionIds = segmentaionIds;
	}

	public List<Integer> getInvestStages() {
		return investStages;
	}

	public void setInvestStages(List<Integer> investStages) {
		this.investStages = investStages;
	}

	public List<String> getCitys() {
		return citys;
	}

	public void setCitys(List<String> citys) {
		this.citys = citys;
	}

	public List<String> getSelfDefCitys() {
		return selfDefCitys;
	}

	public void setSelfDefCitys(List<String> selfDefCitys) {
		this.selfDefCitys = selfDefCitys;
	}

	public List<String> getBusinesses() {
		return businesses;
	}

	public void setBusinesses(List<String> businesses) {
		this.businesses = businesses;
	}

	public Integer getProjectSendBId() {
        return projectSendBId;
    }

    public void setProjectSendBId(Integer projectSendBId) {
        this.projectSendBId = projectSendBId;
    }


    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    public BigDecimal getStockRatio() {
        return stockRatio;
    }

    public void setStockRatio(BigDecimal stockRatio) {
        this.stockRatio = stockRatio;
    }

    public String getMemberDesc() {
        return memberDesc;
    }

    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
    }

    public List<String> getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(List<String> workExperience) {
        this.workExperience = workExperience;
    }

    public List<String> getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(List<String> educationExperience) {
        this.educationExperience = educationExperience;
    }
}
