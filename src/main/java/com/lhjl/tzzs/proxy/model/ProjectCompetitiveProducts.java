package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_competitive_products")
public class ProjectCompetitiveProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 竞品名称
     */
    @Column(name = "competitive_products_name")
    private String competitiveProductsName;

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
     * 获取项目id
     *
     * @return project_id - 项目id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目id
     *
     * @param projectId 项目id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取竞品名称
     *
     * @return competitive_products_name - 竞品名称
     */
    public String getCompetitiveProductsName() {
        return competitiveProductsName;
    }

    /**
     * 设置竞品名称
     *
     * @param competitiveProductsName 竞品名称
     */
    public void setCompetitiveProductsName(String competitiveProductsName) {
        this.competitiveProductsName = competitiveProductsName;
    }
}