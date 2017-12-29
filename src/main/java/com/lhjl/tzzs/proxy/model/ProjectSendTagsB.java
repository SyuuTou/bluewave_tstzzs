package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_tags_b")
public class ProjectSendTagsB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目id
     */
    @Column(name = "project_send_b_id")
    private Integer projectSendBId;

    /**
     * tag名称
     */
    @Column(name = "tag_name")
    private String tagName;

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
     * 获取tag名称
     *
     * @return tag_name - tag名称
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置tag名称
     *
     * @param tagName tag名称
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
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