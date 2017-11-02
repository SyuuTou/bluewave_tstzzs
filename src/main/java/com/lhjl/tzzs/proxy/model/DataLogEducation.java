package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "data_log_education")
public class DataLogEducation {
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 记录的id
     */
    @Column(name = "log_id")
    private Integer logId;

    /**
     * 教育
     */
    @Column(name = "education_name")
    private String educationName;

    /**
     * 用户的id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 获取表id
     *
     * @return id - 表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置表id
     *
     * @param id 表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取记录的id
     *
     * @return log_id - 记录的id
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * 设置记录的id
     *
     * @param logId 记录的id
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * 获取教育
     *
     * @return education_name - 教育
     */
    public String getEducationName() {
        return educationName;
    }

    /**
     * 设置教育
     *
     * @param educationName 教育
     */
    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    /**
     * 获取用户的id
     *
     * @return user_id - 用户的id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户的id
     *
     * @param userId 用户的id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}