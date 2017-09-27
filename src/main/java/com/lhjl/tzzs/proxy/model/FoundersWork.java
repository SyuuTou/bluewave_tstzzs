package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "founders_work")
public class FoundersWork {
    /**
     * 创始人ID
     */
    @Column(name = "founder_id")
    private Integer founderId;

    /**
     * 工作经历
     */
    @Column(name = "work_experience")
    private String workExperience;

    /**
     * 获取创始人ID
     *
     * @return founder_id - 创始人ID
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