package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "data_log_work")
public class DataLogWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 记录id
     */
    @Column(name = "log_id")
    private Integer logId;

    /**
     * 工作
     */
    @Column(name = "work_name")
    private String workName;

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
     * 获取记录id
     *
     * @return log_id - 记录id
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * 设置记录id
     *
     * @param logId 记录id
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * 获取工作
     *
     * @return work_name - 工作
     */
    public String getWorkName() {
        return workName;
    }

    /**
     * 设置工作
     *
     * @param workName 工作
     */
    public void setWorkName(String workName) {
        this.workName = workName;
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