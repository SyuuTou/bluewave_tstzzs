package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institutions_member_work")
public class InvestmentInstitutionsMemberWork {
    /**
     * 团队成员ID
     */
    @Id
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 团队工作经历
     */
    @Column(name = "work_experience")
    private String workExperience;

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
     * 获取团队工作经历
     *
     * @return work_experience - 团队工作经历
     */
    public String getWorkExperience() {
        return workExperience;
    }

    /**
     * 设置团队工作经历
     *
     * @param workExperience 团队工作经历
     */
    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}