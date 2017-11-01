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
     * 工作id
     */
    @Column(name = "work_id")
    private Integer workId;

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
     * 获取工作id
     *
     * @return work_id - 工作id
     */
    public Integer getWorkId() {
        return workId;
    }

    /**
     * 设置工作id
     *
     * @param workId 工作id
     */
    public void setWorkId(Integer workId) {
        this.workId = workId;
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