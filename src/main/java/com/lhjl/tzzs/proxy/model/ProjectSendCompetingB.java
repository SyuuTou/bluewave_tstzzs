package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_competing_b")
public class ProjectSendCompetingB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目id
     */
    @Column(name = "project_send_b_id")
    private Integer projectSendBId;

    /**
     * 竞品名称
     */
    @Column(name = "competing_name")
    private String competingName;

    /**
     * 应用id
     */
    private Integer appid;

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
     * 获取提交项目id
     *
     * @return project_send_b_id - 提交项目id
     */
    public Integer getProjectSendBId() {
        return projectSendBId;
    }

    /**
     * 设置提交项目id
     *
     * @param projectSendBId 提交项目id
     */
    public void setProjectSendBId(Integer projectSendBId) {
        this.projectSendBId = projectSendBId;
    }

    /**
     * 获取竞品名称
     *
     * @return competing_name - 竞品名称
     */
    public String getCompetingName() {
        return competingName;
    }

    /**
     * 设置竞品名称
     *
     * @param competingName 竞品名称
     */
    public void setCompetingName(String competingName) {
        this.competingName = competingName;
    }

    /**
     * 获取应用id
     *
     * @return appid - 应用id
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * 设置应用id
     *
     * @param appid 应用id
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }
}