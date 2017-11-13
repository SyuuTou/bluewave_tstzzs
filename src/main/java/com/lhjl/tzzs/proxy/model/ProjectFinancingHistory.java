package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_financing_history")
public class ProjectFinancingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目提交记录表id
     */
    @Column(name = "project_send_log_id")
    private String projectSendLogId;

    /**
     * 融资历史信息
     */
    private String history;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取项目提交记录表id
     *
     * @return project_send_log_id - 项目提交记录表id
     */
    public String getProjectSendLogId() {
        return projectSendLogId;
    }

    /**
     * 设置项目提交记录表id
     *
     * @param projectSendLogId 项目提交记录表id
     */
    public void setProjectSendLogId(String projectSendLogId) {
        this.projectSendLogId = projectSendLogId;
    }

    /**
     * 获取融资历史信息
     *
     * @return history - 融资历史信息
     */
    public String getHistory() {
        return history;
    }

    /**
     * 设置融资历史信息
     *
     * @param history 融资历史信息
     */
    public void setHistory(String history) {
        this.history = history;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}