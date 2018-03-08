package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
public class CollectProjectAuditTeamDto{
	//审核项目记录表主键id
    private Integer projectId;
    //团队介绍
    private String teamIntroduction;
    //采集项目审核团队成员列表
    private List<CollectProjectAuditMemberDto> collectProjectAuditMemberDtoList;
    
    //团队成员展开的详细信息
//    private CollectProjectAuditMemberDetailsDto collectProjectAuditMemberDetailsDto;
    
    
    public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getTeamIntroduction() {
		return teamIntroduction;
	}
	public void setTeamIntroduction(String teamIntroduction) {
		this.teamIntroduction = teamIntroduction;
	}
	public List<CollectProjectAuditMemberDto> getCollectProjectAuditMemberDtoList() {
		return collectProjectAuditMemberDtoList;
	}
	public void setCollectProjectAuditMemberDtoList(List<CollectProjectAuditMemberDto> collectProjectAuditMemberDtoList) {
		this.collectProjectAuditMemberDtoList = collectProjectAuditMemberDtoList;
	}
	
	@Override
	public String toString() {
		return "CollectProjectAuditTeamDto [projectId=" + projectId + ", teamIntroduction=" + teamIntroduction
				+ ", collectProjectAuditMemberDtoList=" + collectProjectAuditMemberDtoList + "]";
	}

		/**
     * 团队成员展开详细信息的静态内部类
     * @author IdataVC
     *
     */
    /*public class CollectProjectAuditMemberDetailsDto{
    	*//**
    	 * 头像
    	 *//*
    	private String headPicture;  
    	*//**
    	 * 高清图片
    	 *//*
        private String picture;

        private String email;

        private String weiChat;
        *//**
         * 所属团队
         *//*
        private Integer teamId;
        *//**
         * 自定义团队
         *//*
        private String selfDefTeam;
        *//**
         * 出生年月
         *//*
        private String birthDate;
        *//**
         * 任职时间
         *//*
        private String tenureTime;

        private Integer sex;

        private Integer diploma;
        *//**
         * 国籍
         *//*
        private Integer nationality;
        *//**
         * 关注领域
         *//*
        private Integer[] segmentaionIds;
        *//**
         * 投资阶段
         *//*
        private List<Integer> investStages;
        *//**
         * 所在城市
         *//*
        private String[] citys;
        
        private String[] selfDefCitys;
        *//**
         * 创业经历
         *//*
        private String[] businesses;
        *//**
         * 创业经历描述
         *//*
        private String businessDesc;

        private String educationExperienceDesc;
        
        private String workExperienceDesc;
        
        //以下字段属于同团队成员的重叠字段
        *//**
         * 团队成员权重权重
         *//*
        private Integer weight;
        *//**
         * 
         *//*
        private String phone;
        *//**
         * 是否隐藏
         * 1 隐藏
         * 0 显示
         *//*
        private Integer isHide;
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
		public String getBirthDate() {
			return birthDate;
		}
		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
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
		public Integer[] getSegmentaionIds() {
			return segmentaionIds;
		}
		public void setSegmentaionIds(Integer[] segmentaionIds) {
			this.segmentaionIds = segmentaionIds;
		}
		public List<Integer> getInvestStages() {
			return investStages;
		}
		public void setInvestStages(List<Integer> investStages) {
			this.investStages = investStages;
		}
		public String[] getCitys() {
			return citys;
		}
		public void setCitys(String[] citys) {
			this.citys = citys;
		}
		public String[] getSelfDefCitys() {
			return selfDefCitys;
		}
		public void setSelfDefCitys(String[] selfDefCitys) {
			this.selfDefCitys = selfDefCitys;
		}
		public String[] getBusinesses() {
			return businesses;
		}
		public void setBusinesses(String[] businesses) {
			this.businesses = businesses;
		}
		public String getBusinessDesc() {
			return businessDesc;
		}
		public void setBusinessDesc(String businessDesc) {
			this.businessDesc = businessDesc;
		}
		public String getEducationExperienceDesc() {
			return educationExperienceDesc;
		}
		public void setEducationExperienceDesc(String educationExperienceDesc) {
			this.educationExperienceDesc = educationExperienceDesc;
		}
		public String getWorkExperienceDesc() {
			return workExperienceDesc;
		}
		public void setWorkExperienceDesc(String workExperienceDesc) {
			this.workExperienceDesc = workExperienceDesc;
		}
		public Integer getWeight() {
			return weight;
		}
		public void setWeight(Integer weight) {
			this.weight = weight;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public Integer getIsHide() {
			return isHide;
		}
		public void setIsHide(Integer isHide) {
			this.isHide = isHide;
		}
		@Override
		public String toString() {
			return "CollectProjectAuditMemberDetailsDto [headPicture=" + headPicture + ", picture=" + picture
					+ ", email=" + email + ", weiChat=" + weiChat + ", teamId=" + teamId + ", selfDefTeam="
					+ selfDefTeam + ", birthDate=" + birthDate + ", tenureTime=" + tenureTime + ", sex=" + sex
					+ ", diploma=" + diploma + ", nationality=" + nationality + ", segmentaionIds="
					+ Arrays.toString(segmentaionIds) + ", investStages=" + investStages + ", citys="
					+ Arrays.toString(citys) + ", selfDefCitys=" + Arrays.toString(selfDefCitys) + ", businesses="
					+ Arrays.toString(businesses) + ", businessDesc=" + businessDesc + ", educationExperienceDesc="
					+ educationExperienceDesc + ", workExperienceDesc=" + workExperienceDesc + ", weight=" + weight
					+ ", phone=" + phone + ", isHide=" + isHide + "]";
		}

    }*/
    //定义团队成员的静态内部类
    public static class CollectProjectAuditMemberDto implements Comparable<CollectProjectAuditMemberDto>{
    	//用户前端的展示id
        private Integer sortId;
        //团队成员的id
        private Integer memberId;
        //成员姓名
        private String memberName;
        //职务
        private String position;
        /**
         * 股份占比
         */
        private BigDecimal stockPer;
        /**
         * 成员简介
         */
        private String kernelDesc;
        

        private List<String> workExperiences;

        private List<String> educationExperience;
        
        /**
         * 团队成员权重权重
         */
        private Integer weight;
        /**
         * 0 离职
         * 1 在职
         * null 无
         */
        private Integer isOnJob;
        /**
         * 手机号
         */
        private String phone;
        /**
         * 是否隐藏
         * 1 隐藏
         * 0 显示
         */
        private Integer isHide;
        /**
    	 * 头像
    	 */
    	private String headPicture;  
    	/**
    	 * 高清图片
    	 */
        private String picture;

        private String email;

        private String weiChat;
        /**
         * 所属团队
         */
        private Integer teamId;
        /**
         * 自定义团队
         */
        private String selfDefTeam;
        /**
         * 出生年月
         */
        private String birthDate;
        /**
         * 任职时间
         */
        private String tenureTime;

        private Integer sex;

        private Integer diploma;
        /**
         * 国籍
         */
        private Integer nationality;
        /**
         * 关注领域
         */
        private Integer[] segmentaionIds;
        /**
         * 投资阶段
         */
        private List<Integer> investStages;
        /**
         * 所在城市
         */
        private String[] citys;
        
        private String[] selfDefCitys;
        /**
         * 创业经历
         */
        private String[] businesses;
        /**
         * 创业经历描述
         */
        private String businessDesc;

        private String educationExperienceDesc;
        
        private String workExperienceDesc;

		public Integer getSortId() {
			return sortId;
		}

		public void setSortId(Integer sortId) {
			this.sortId = sortId;
		}

		public Integer getMemberId() {
			return memberId;
		}

		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}

		public String getMemberName() {
			return memberName;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public BigDecimal getStockPer() {
			return stockPer;
		}

		public void setStockPer(BigDecimal stockPer) {
			this.stockPer = stockPer;
		}

		public String getKernelDesc() {
			return kernelDesc;
		}

		public void setKernelDesc(String kernelDesc) {
			this.kernelDesc = kernelDesc;
		}

		public List<String> getWorkExperiences() {
			return workExperiences;
		}

		public void setWorkExperiences(List<String> workExperiences) {
			this.workExperiences = workExperiences;
		}

		public List<String> getEducationExperience() {
			return educationExperience;
		}

		public void setEducationExperience(List<String> educationExperience) {
			this.educationExperience = educationExperience;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public Integer getIsOnJob() {
			return isOnJob;
		}

		public void setIsOnJob(Integer isOnJob) {
			this.isOnJob = isOnJob;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Integer getIsHide() {
			return isHide;
		}

		public void setIsHide(Integer isHide) {
			this.isHide = isHide;
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

		public String getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
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

		public Integer[] getSegmentaionIds() {
			return segmentaionIds;
		}

		public void setSegmentaionIds(Integer[] segmentaionIds) {
			this.segmentaionIds = segmentaionIds;
		}

		public List<Integer> getInvestStages() {
			return investStages;
		}

		public void setInvestStages(List<Integer> investStages) {
			this.investStages = investStages;
		}

		public String[] getCitys() {
			return citys;
		}

		public void setCitys(String[] citys) {
			this.citys = citys;
		}

		public String[] getSelfDefCitys() {
			return selfDefCitys;
		}

		public void setSelfDefCitys(String[] selfDefCitys) {
			this.selfDefCitys = selfDefCitys;
		}

		public String[] getBusinesses() {
			return businesses;
		}

		public void setBusinesses(String[] businesses) {
			this.businesses = businesses;
		}

		public String getBusinessDesc() {
			return businessDesc;
		}

		public void setBusinessDesc(String businessDesc) {
			this.businessDesc = businessDesc;
		}

		public String getEducationExperienceDesc() {
			return educationExperienceDesc;
		}

		public void setEducationExperienceDesc(String educationExperienceDesc) {
			this.educationExperienceDesc = educationExperienceDesc;
		}

		public String getWorkExperienceDesc() {
			return workExperienceDesc;
		}

		public void setWorkExperienceDesc(String workExperienceDesc) {
			this.workExperienceDesc = workExperienceDesc;
		}

		/**
		 * 比较器
		 */
		@Override
        public int compareTo(CollectProjectAuditMemberDto collectProjectAuditMemberDto) {
        	int result = this.weight > collectProjectAuditMemberDto.weight ? -1 : (this.weight == collectProjectAuditMemberDto.weight ? 0 : 1);
            return result;
        }

		@Override
		public String toString() {
			return "CollectProjectAuditMemberDto [sortId=" + sortId + ", memberId=" + memberId + ", memberName="
					+ memberName + ", position=" + position + ", stockPer=" + stockPer + ", kernelDesc=" + kernelDesc
					+ ", workExperiences=" + workExperiences + ", educationExperience=" + educationExperience
					+ ", weight=" + weight + ", isOnJob=" + isOnJob + ", phone=" + phone + ", isHide=" + isHide
					+ ", headPicture=" + headPicture + ", picture=" + picture + ", email=" + email + ", weiChat="
					+ weiChat + ", teamId=" + teamId + ", selfDefTeam=" + selfDefTeam + ", birthDate=" + birthDate
					+ ", tenureTime=" + tenureTime + ", sex=" + sex + ", diploma=" + diploma + ", nationality="
					+ nationality + ", segmentaionIds=" + Arrays.toString(segmentaionIds) + ", investStages="
					+ investStages + ", citys=" + Arrays.toString(citys) + ", selfDefCitys="
					+ Arrays.toString(selfDefCitys) + ", businesses=" + Arrays.toString(businesses) + ", businessDesc="
					+ businessDesc + ", educationExperienceDesc=" + educationExperienceDesc + ", workExperienceDesc="
					+ workExperienceDesc + "]";
		}

    }
    
}
