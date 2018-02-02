package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investor_education_experience")
public class InvestorEducationExperience {
    /**
     * 投资人id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 教育经历
     */
    @Column(name = "education_experience")
    private String educationExperience;

    /**
     * 获取投资人id
     *
     * @return id - 投资人id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置投资人id
     *
     * @param id 投资人id
     */
    public void setId(Integer id) {
        this.id = id;
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