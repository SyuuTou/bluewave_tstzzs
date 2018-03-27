package com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/18.
 */
public class ProjectTeamMemberOutputDto implements Comparable<ProjectTeamMemberOutputDto>{

    /**
     * 主体Id
     */
    private Integer projectId;

    /**
     * 成员ID
     */
    private Integer memberId;

    /**
     * 权重
     */
    private Integer weight = 0;

    /**
     * 姓名
     */
    private String name;

    /**
     * 职务
     */
    private String jobTitle;

    /**
     * 成员介绍
     */
    private String memberDesc;

    /**
     * 工作经历
     */
    private List<String> workExperiences;

    /**
     * 教育经历
     */
    private List<String> educationExperience;

    /**
     * 是否在职
     */
    private Integer isOnJob;

    /**
     * 是否隐藏
     */
    private Integer isDelete;

    /**
     * 头像
     */
    private String headPicture;

    /**
     * 高清照片
     */
    private String picture;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信
     */
    private String weiChat;

    /**
     * 所属团队ID
     */
    private Integer teamId;

    /**
     * 自定义团队
     */
    private String selfDefTeam;

    /**
     * 出生日期
     */
    private String birthDay;

    /**
     * 任职时间
     */
    private String tenureTime;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 学历
     */
    private Integer diploma;

    /**
     * 国籍
     */
    private Integer nationality;

    /**
     * 关注领域
     */
    private List<Integer> focusDomain;

    /**
     * 投资阶段
     */
    private List<Integer> investStages;

    /**
     * 股票
     */
    private BigDecimal stockPer;

    /**
     * 所在城市
     */
    private List<String> citys;

    /**
     * 自定义城市
     */
    private List<String> selfcitys;

    /**
     * 创业经历
     */
    private List<String> businesses;

    /**
     * 创业经历描述
     */
    private String businessDesc;

    /**
     * 工作经历描述
     */
    private String workDesc;

    /**
     * 教育经历描述
     */
    private String educationDesc;
    

    public List<String> getEducationExperience() {
		return educationExperience;
	}

	public void setEducationExperience(List<String> educationExperience) {
		this.educationExperience = educationExperience;
	}

	/**
     * 是否隐藏，默认不隐藏，不隐藏为1
     */
    private Integer isHide;

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMemberDesc() {
        return memberDesc;
    }

    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
    }


    public List<String> getWorkExperiences() {
		return workExperiences;
	}

	public void setWorkExperiences(List<String> workExperiences) {
		this.workExperiences = workExperiences;
	}


    public Integer getIsOnJob() {
        return isOnJob;
    }

    public void setIsOnJob(Integer isOnJob) {
        this.isOnJob = isOnJob;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeiChat() {
        return weiChat;
    }

    public void setWeiChat(String weiChat) {
        this.weiChat = weiChat;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getSelfDefTeam() {
        return selfDefTeam;
    }

    public void setSelfDefTeam(String selfDefTeam) {
        this.selfDefTeam = selfDefTeam;
    }


    public List<Integer> getFocusDomain() {
		return focusDomain;
	}

	public void setFocusDomain(List<Integer> focusDomain) {
		this.focusDomain = focusDomain;
	}


    public List<Integer> getInvestStages() {
		return investStages;
	}

	public void setInvestStages(List<Integer> investStages) {
		this.investStages = investStages;
	}

	public BigDecimal getStockPer() {
        return stockPer;
    }

    public void setStockPer(BigDecimal stockPer) {
        this.stockPer = stockPer;
    }


    public List<String> getCitys() {
		return citys;
	}

	public void setCitys(List<String> citys) {
		this.citys = citys;
	}

    public List<String> getSelfcitys() {
		return selfcitys;
	}

	public void setSelfcitys(List<String> selfcitys) {
		this.selfcitys = selfcitys;
	}

	public List<String> getBusinesses() {
		return businesses;
	}

	public void setBusinesses(List<String> businesses) {
		this.businesses = businesses;
	}

	public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public String getEducationDesc() {
        return educationDesc;
    }

    public void setEducationDesc(String educationDesc) {
        this.educationDesc = educationDesc;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public int compareTo(ProjectTeamMemberOutputDto projectTeamMemberOutputDto) {

        int result = this.weight > projectTeamMemberOutputDto.weight ? 1 : (this.weight == projectTeamMemberOutputDto.weight ? 0 : -1);
        return result;
    }
}
