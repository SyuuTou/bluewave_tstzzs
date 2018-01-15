package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investor_demand_stage")
public class InvestorDemandStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目阶段元数据表id
     */
    @Column(name = "meta_project_stage_id")
    private Integer metaProjectStageId;

    /**
     * 融资需求表id
     */
    @Column(name = "investor_demand_id")
    private Integer investorDemandId;

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
     * 获取项目阶段元数据表id
     *
     * @return meta_project_stage_id - 项目阶段元数据表id
     */
    public Integer getMetaProjectStageId() {
        return metaProjectStageId;
    }

    /**
     * 设置项目阶段元数据表id
     *
     * @param metaProjectStageId 项目阶段元数据表id
     */
    public void setMetaProjectStageId(Integer metaProjectStageId) {
        this.metaProjectStageId = metaProjectStageId;
    }

    /**
     * 获取融资需求表id
     *
     * @return investor_demand_id - 融资需求表id
     */
    public Integer getInvestorDemandId() {
        return investorDemandId;
    }

    /**
     * 设置融资需求表id
     *
     * @param investorDemandId 融资需求表id
     */
    public void setInvestorDemandId(Integer investorDemandId) {
        this.investorDemandId = investorDemandId;
    }
}