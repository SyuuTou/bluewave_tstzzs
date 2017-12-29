package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_segmentation_b")
public class ProjectSendSegmentationB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目表id
     */
    @Column(name = "project_send_b_id")
    private Integer projectSendBId;

    /**
     * 领域名称
     */
    @Column(name = "segmentation_name")
    private String segmentationName;

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
     * 获取提交项目表id
     *
     * @return project_send_b_id - 提交项目表id
     */
    public Integer getProjectSendBId() {
        return projectSendBId;
    }

    /**
     * 设置提交项目表id
     *
     * @param projectSendBId 提交项目表id
     */
    public void setProjectSendBId(Integer projectSendBId) {
        this.projectSendBId = projectSendBId;
    }

    /**
     * 获取领域名称
     *
     * @return segmentation_name - 领域名称
     */
    public String getSegmentationName() {
        return segmentationName;
    }

    /**
     * 设置领域名称
     *
     * @param segmentationName 领域名称
     */
    public void setSegmentationName(String segmentationName) {
        this.segmentationName = segmentationName;
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