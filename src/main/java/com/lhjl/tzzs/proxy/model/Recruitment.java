package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目ID
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 岗位类型
     */
    @Column(name = "job_type_id")
    private Integer jobTypeId;

    /**
     * 职位名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 工作地点
     */
    @Column(name = "address_details")
    private String addressDetails;

    /**
     * 是否全职：0代表否；1代表是
     */
    @Column(name = "full_time")
    private Integer fullTime;

    /**
     * 职位简介
     */
    @Column(name = "job_comment")
    private String jobComment;

    /**
     * 最低薪水
     */
    @Column(name = "salary_low")
    private BigDecimal salaryLow;

    /**
     * 最高薪水
     */
    @Column(name = "salary_high")
    private BigDecimal salaryHigh;

    /**
     * 福利
     */
    private String welfare;

    /**
     * 技能需求
     */
    @Column(name = "skill_requirment")
    private String skillRequirment;

    /**
     * 最低期权
     */
    @Column(name = "share_option_low")
    private BigDecimal shareOptionLow;

    /**
     * 最高期权
     */
    @Column(name = "share_option_high")
    private BigDecimal shareOptionHigh;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 创建用户
     */
    @Column(name = "created_user_id")
    private Integer createdUserId;

    /**
     * 更新用户
     */
    @Column(name = "updated_user_id")
    private Integer updatedUserId;

    /**
     * 删除标志:0代表有效；1代表无效
     */
    private Integer yn;
    
    /**
     * 主体类型
     */
    @Transient
    private Integer subjectType;
    
    public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

	/**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取项目ID
     *
     * @return company_id - 项目ID
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置项目ID
     *
     * @param companyId 项目ID
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取岗位类型
     *
     * @return job_type_id - 岗位类型
     */
    public Integer getJobTypeId() {
        return jobTypeId;
    }

    /**
     * 设置岗位类型
     *
     * @param jobTypeId 岗位类型
     */
    public void setJobTypeId(Integer jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    /**
     * 获取职位名称
     *
     * @return job_name - 职位名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置职位名称
     *
     * @param jobName 职位名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 获取工作地点
     *
     * @return address_details - 工作地点
     */
    public String getAddressDetails() {
        return addressDetails;
    }

    /**
     * 设置工作地点
     *
     * @param addressDetails 工作地点
     */
    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    /**
     * 获取是否全职：0代表否；1代表是
     *
     * @return full_time - 是否全职：0代表否；1代表是
     */
    public Integer getFullTime() {
        return fullTime;
    }

    /**
     * 设置是否全职：0代表否；1代表是
     *
     * @param fullTime 是否全职：0代表否；1代表是
     */
    public void setFullTime(Integer fullTime) {
        this.fullTime = fullTime;
    }

    /**
     * 获取职位简介
     *
     * @return job_comment - 职位简介
     */
    public String getJobComment() {
        return jobComment;
    }

    /**
     * 设置职位简介
     *
     * @param jobComment 职位简介
     */
    public void setJobComment(String jobComment) {
        this.jobComment = jobComment;
    }

    /**
     * 获取最低薪水
     *
     * @return salary_low - 最低薪水
     */
    public BigDecimal getSalaryLow() {
        return salaryLow;
    }

    /**
     * 设置最低薪水
     *
     * @param salaryLow 最低薪水
     */
    public void setSalaryLow(BigDecimal salaryLow) {
        this.salaryLow = salaryLow;
    }

    /**
     * 获取最高薪水
     *
     * @return salary_high - 最高薪水
     */
    public BigDecimal getSalaryHigh() {
        return salaryHigh;
    }

    /**
     * 设置最高薪水
     *
     * @param salaryHigh 最高薪水
     */
    public void setSalaryHigh(BigDecimal salaryHigh) {
        this.salaryHigh = salaryHigh;
    }

    /**
     * 获取福利
     *
     * @return welfare - 福利
     */
    public String getWelfare() {
        return welfare;
    }

    /**
     * 设置福利
     *
     * @param welfare 福利
     */
    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    /**
     * 获取技能需求
     *
     * @return skill_requirment - 技能需求
     */
    public String getSkillRequirment() {
        return skillRequirment;
    }

    /**
     * 设置技能需求
     *
     * @param skillRequirment 技能需求
     */
    public void setSkillRequirment(String skillRequirment) {
        this.skillRequirment = skillRequirment;
    }

    /**
     * 获取最低期权
     *
     * @return share_option_low - 最低期权
     */
    public BigDecimal getShareOptionLow() {
        return shareOptionLow;
    }

    /**
     * 设置最低期权
     *
     * @param shareOptionLow 最低期权
     */
    public void setShareOptionLow(BigDecimal shareOptionLow) {
        this.shareOptionLow = shareOptionLow;
    }

    /**
     * 获取最高期权
     *
     * @return share_option_high - 最高期权
     */
    public BigDecimal getShareOptionHigh() {
        return shareOptionHigh;
    }

    /**
     * 设置最高期权
     *
     * @param shareOptionHigh 最高期权
     */
    public void setShareOptionHigh(BigDecimal shareOptionHigh) {
        this.shareOptionHigh = shareOptionHigh;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后一次更新时间
     *
     * @return last_update_time - 最后一次更新时间
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置最后一次更新时间
     *
     * @param lastUpdateTime 最后一次更新时间
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取创建用户
     *
     * @return created_user_id - 创建用户
     */
    public Integer getCreatedUserId() {
        return createdUserId;
    }

    /**
     * 设置创建用户
     *
     * @param createdUserId 创建用户
     */
    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
    }

    /**
     * 获取更新用户
     *
     * @return updated_user_id - 更新用户
     */
    public Integer getUpdatedUserId() {
        return updatedUserId;
    }

    /**
     * 设置更新用户
     *
     * @param updatedUserId 更新用户
     */
    public void setUpdatedUserId(Integer updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    /**
     * 获取删除标志:0代表有效；1代表无效
     *
     * @return yn - 删除标志:0代表有效；1代表无效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置删除标志:0代表有效；1代表无效
     *
     * @param yn 删除标志:0代表有效；1代表无效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}