package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "elegant_service_relevant_project")
public class ElegantServiceRelevantProject {
    /**
     * 精选
     */
    @Id
    @Column(name = "elegant_service_id")
    private Integer elegantServiceId;

    @Id
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "project_short_name")
    private String projectShortName;

    @Column(name = "data_type")
    private Integer dataType;


    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取精选
     *
     * @return elegant_service_id - 精选
     */
    public Integer getElegantServiceId() {
        return elegantServiceId;
    }

    /**
     * 设置精选
     *
     * @param elegantServiceId 精选
     */
    public void setElegantServiceId(Integer elegantServiceId) {
        this.elegantServiceId = elegantServiceId;
    }

    /**
     * @return project_id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * @return project_short_name
     */
    public String getProjectShortName() {
        return projectShortName;
    }

    /**
     * @param projectShortName
     */
    public void setProjectShortName(String projectShortName) {
        this.projectShortName = projectShortName;
    }
}