package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
public class CollectProjectAuditTeamDto{

    private Integer projectId;

    private String teamIntroduction;

    private List<CollectProjectAuditMemberDto> collectProjectAuditMemberDtoList;

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

    public static class CollectProjectAuditMemberDto implements Comparable<CollectProjectAuditMemberDto>{

        private Integer sortId;

        private Integer memberId;

        private String memberName;

        private String position;

        private String kernelDesc;

        private String phone;

        private String[] workExperiences;

        private String[] educationExperience;

        private Integer isOnJob;

        private String headPicture;

        private String picture;

        private String email;

        private String weiChat;

        private Integer teamId;

        private String selfDefTeam;

        private String birthDate;

        private String tenureTime;

        private Integer sex;

        private Integer diploma;

        private Integer nationality;

        private Integer[] segmentaionIds;

        private BigDecimal stockPer;

        private String[] citys;

        private String[] selfDefCitys;

        private String[] businesses;

        private String businessDesc;

        private String workExperienceDesc;

        private String educationExperienceDesc;

        private Integer weight;

        private Integer isHide;

        public Integer getMemberId() {
            return memberId;
        }

        public Integer getSortId() {
            return sortId;
        }

        public void setSortId(Integer sortId) {
            this.sortId = sortId;
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

        public String getKernelDesc() {
            return kernelDesc;
        }

        public void setKernelDesc(String kernelDesc) {
            this.kernelDesc = kernelDesc;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String[] getWorkExperiences() {
            return workExperiences;
        }

        public void setWorkExperiences(String[] workExperiences) {
            this.workExperiences = workExperiences;
        }

        public String[] getEducationExperience() {
            return educationExperience;
        }

        public void setEducationExperience(String[] educationExperience) {
            this.educationExperience = educationExperience;
        }

        public Integer getIsOnJob() {
            return isOnJob;
        }

        public void setIsOnJob(Integer isOnJob) {
            this.isOnJob = isOnJob;
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

        public BigDecimal getStockPer() {
            return stockPer;
        }

        public void setStockPer(BigDecimal stockPer) {
            this.stockPer = stockPer;
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

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public Integer getIsHide() {
            return isHide;
        }

        public void setIsHide(Integer isHide) {
            this.isHide = isHide;
        }

        @Override
        public int compareTo(CollectProjectAuditMemberDto collectProjectAuditMemberDto) {

            int result = this.weight > collectProjectAuditMemberDto.weight ? 1 : (this.weight == collectProjectAuditMemberDto.weight ? 0 : -1);
            return result;
        }
    }

}
