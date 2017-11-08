package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "admin_project_rating_log")
public class AdminProjectRatingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 项目等级：0表示D级，1表示C级，2表示B级，3表示A级，4表示S级
     */
    @Column(name = "rating_stage")
    private Integer ratingStage;

    /**
     * 评级时间
     */
    @Column(name = "rating_time")
    private Date ratingTime;

    /**
     * 评级说明
     */
    @Column(name = "rating_description")
    private String ratingDescription;

    /**
     * 评级人
     */
    @Column(name = "rating_admin_name")
    private String ratingAdminName;

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
     * 获取项目等级：0表示D级，1表示C级，2表示B级，3表示A级，4表示S级
     *
     * @return rating_stage - 项目等级：0表示D级，1表示C级，2表示B级，3表示A级，4表示S级
     */
    public Integer getRatingStage() {
        return ratingStage;
    }

    /**
     * 设置项目等级：0表示D级，1表示C级，2表示B级，3表示A级，4表示S级
     *
     * @param ratingStage 项目等级：0表示D级，1表示C级，2表示B级，3表示A级，4表示S级
     */
    public void setRatingStage(Integer ratingStage) {
        this.ratingStage = ratingStage;
    }

    /**
     * 获取评级时间
     *
     * @return rating_time - 评级时间
     */
    public Date getRatingTime() {
        return ratingTime;
    }

    /**
     * 设置评级时间
     *
     * @param ratingTime 评级时间
     */
    public void setRatingTime(Date ratingTime) {
        this.ratingTime = ratingTime;
    }

    /**
     * 获取评级说明
     *
     * @return rating_description - 评级说明
     */
    public String getRatingDescription() {
        return ratingDescription;
    }

    /**
     * 设置评级说明
     *
     * @param ratingDescription 评级说明
     */
    public void setRatingDescription(String ratingDescription) {
        this.ratingDescription = ratingDescription;
    }

    /**
     * 获取评级人
     *
     * @return rating_admin_name - 评级人
     */
    public String getRatingAdminName() {
        return ratingAdminName;
    }

    /**
     * 设置评级人
     *
     * @param ratingAdminName 评级人
     */
    public void setRatingAdminName(String ratingAdminName) {
        this.ratingAdminName = ratingAdminName;
    }
}