package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institutions_member_education")
public class InvestmentInstitutionsMemberEducation {
    /**
     * 团队成员ID
     */
    @Id
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 团队成员教育经历
     */
    @Column(name = "education_experience")
    private String educationExperience;

    /**
     * 获取团队成员ID
     *
     * @return member_id - 团队成员ID
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 设置团队成员ID
     *
     * @param memberId 团队成员ID
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取团队成员教育经历
     *
     * @return education_experience - 团队成员教育经历
     */
    public String getEducationExperience() {
        return educationExperience;
    }

    /**
     * 设置团队成员教育经历
     *
     * @param educationExperience 团队成员教育经历
     */
    public void setEducationExperience(String educationExperience) {
        this.educationExperience = educationExperience;
    }
}