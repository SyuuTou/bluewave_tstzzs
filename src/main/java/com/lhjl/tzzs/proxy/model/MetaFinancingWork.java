package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_financing_work")
public class MetaFinancingWork {
    @Id
    @Column(name = "financing_id")
    private Integer financingId;

    @Id
    @Column(name = "work_experience")
    private String workExperience;

    /**
     * @return financing_id
     */
    public Integer getFinancingId() {
        return financingId;
    }

    /**
     * @param financingId
     */
    public void setFinancingId(Integer financingId) {
        this.financingId = financingId;
    }

    /**
     * @return work_experience
     */
    public String getWorkExperience() {
        return workExperience;
    }

    /**
     * @param workExperience
     */
    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}