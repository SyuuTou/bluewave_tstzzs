package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "recruitment_info")
public class RecruitmentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 招聘需求描述
     */
    private String description;

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
     * 获取招聘需求描述
     *
     * @return description - 招聘需求描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置招聘需求描述
     *
     * @param description 招聘需求描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}