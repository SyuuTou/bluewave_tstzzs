package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investor_work_experience")
public class InvestorWorkExperience {
    /**
     * 投资人id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 工作经历
     */
    @Column(name = "work_experience")
    private String workExperience;

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
     * 获取工作经历
     *
     * @return work_experience - 工作经历
     */
    public String getWorkExperience() {
        return workExperience;
    }

    /**
     * 设置工作经历
     *
     * @param workExperience 工作经历
     */
    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}