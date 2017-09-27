package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_financing_education")
public class MetaFinancingEducation {
    /**
     * 融资纪录ID
     */
    @Id
    @Column(name = "financing_ID")
    private Integer financingId;

    /**
     * 教育经历
     */
    @Id
    @Column(name = "education_experience")
    private String educationExperience;

    /**
     * 获取融资纪录ID
     *
     * @return financing_ID - 融资纪录ID
     */
    public Integer getFinancingId() {
        return financingId;
    }

    /**
     * 设置融资纪录ID
     *
     * @param financingId 融资纪录ID
     */
    public void setFinancingId(Integer financingId) {
        this.financingId = financingId;
    }

    /**
     * 获取教育经历
     *
     * @return education_experience - 教育经历
     */
    public String getEducationExperience() {
        return educationExperience;
    }

    /**
     * 设置教育经历
     *
     * @param educationExperience 教育经历
     */
    public void setEducationExperience(String educationExperience) {
        this.educationExperience = educationExperience;
    }
}