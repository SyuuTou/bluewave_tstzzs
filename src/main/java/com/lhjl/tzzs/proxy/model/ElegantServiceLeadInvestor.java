package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "elegant_service_lead_investor")
public class ElegantServiceLeadInvestor {
    @Id
    @Column(name = "elegant_service_id")
    private Integer elegantServiceId;

    /**
     * 领投类型，0，非领投，1，领投
     */
    @Id
    @Column(name = "lead_investor_type")
    private Integer leadInvestorType;

    /**
     * @return elegant_service_id
     */
    public Integer getElegantServiceId() {
        return elegantServiceId;
    }

    /**
     * @param elegantServiceId
     */
    public void setElegantServiceId(Integer elegantServiceId) {
        this.elegantServiceId = elegantServiceId;
    }

    /**
     * 获取领投类型，0，非领投，1，领投
     *
     * @return lead_investor_type - 领投类型，0，非领投，1，领投
     */
    public Integer getLeadInvestorType() {
        return leadInvestorType;
    }

    /**
     * 设置领投类型，0，非领投，1，领投
     *
     * @param leadInvestorType 领投类型，0，非领投，1，领投
     */
    public void setLeadInvestorType(Integer leadInvestorType) {
        this.leadInvestorType = leadInvestorType;
    }
}