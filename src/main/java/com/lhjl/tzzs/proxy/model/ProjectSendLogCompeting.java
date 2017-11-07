package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_log_competing")
public class ProjectSendLogCompeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目表id
     */
    @Column(name = "project_send_logs_id")
    private Integer projectSendLogsId;

    /**
     * 竞品名称
     */
    @Column(name = "competing_product_name")
    private String competingProductName;

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
     * 获取提交项目表id
     *
     * @return project_send_logs_id - 提交项目表id
     */
    public Integer getProjectSendLogsId() {
        return projectSendLogsId;
    }

    /**
     * 设置提交项目表id
     *
     * @param projectSendLogsId 提交项目表id
     */
    public void setProjectSendLogsId(Integer projectSendLogsId) {
        this.projectSendLogsId = projectSendLogsId;
    }

    /**
     * 获取竞品名称
     *
     * @return competing_product_name - 竞品名称
     */
    public String getCompetingProductName() {
        return competingProductName;
    }

    /**
     * 设置竞品名称
     *
     * @param competingProductName 竞品名称
     */
    public void setCompetingProductName(String competingProductName) {
        this.competingProductName = competingProductName;
    }
}