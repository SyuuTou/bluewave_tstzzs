package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "recruitment_info")
public class RecruitmentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 招聘需求描述
     */
    @Column(name = "description")
    private String description;
    
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
     * 获取项目id
     *
     * @return company_id - 项目id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置项目id
     *
     * @param companyId 项目id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取招聘需求描述
     *
     * @return description - 招聘需求描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置招聘需求描述
     *
     * @param description 招聘需求描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}