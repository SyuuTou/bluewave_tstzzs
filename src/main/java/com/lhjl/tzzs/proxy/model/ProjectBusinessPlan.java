package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_business_plan")
public class ProjectBusinessPlan {
    /**
     * 项目ID
     */
    @Id
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 商业计划书路径
     */
    private String url;

    /**
     * 更新时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取项目ID
     *
     * @return project_id - 项目ID
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目ID
     *
     * @param projectId 项目ID
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取商业计划书路径
     *
     * @return url - 商业计划书路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置商业计划书路径
     *
     * @param url 商业计划书路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取更新时间
     *
     * @return create_time - 更新时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置更新时间
     *
     * @param createTime 更新时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}