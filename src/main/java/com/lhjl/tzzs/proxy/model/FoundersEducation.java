package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "founders_education")
public class FoundersEducation {
    /**
     * 创始人ID
     */
    @Column(name = "founder_ID")
    private Integer founderId;

    /**
     * 教育经历
     */
    @Column(name = "education_experience")
    private String educationExperience;

    /**
     * 获取创始人ID
     *
     * @return founder_ID - 创始人ID
     */
    public Integer getFounderId() {
        return founderId;
    }

    /**
     * 设置创始人ID
     *
     * @param founderId 创始人ID
     */
    public void setFounderId(Integer founderId) {
        this.founderId = founderId;
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