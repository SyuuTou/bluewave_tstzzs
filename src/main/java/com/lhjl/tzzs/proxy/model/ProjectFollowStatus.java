package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_follow_status")
public class ProjectFollowStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 跟进状态元数据表id
     */
    @Column(name = "meta_follow_status_id")
    private Integer metaFollowStatusId;

    /**
     * 备注
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "creat_time")
    private Date creatTime;

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
     * 获取跟进状态元数据表id
     *
     * @return meta_follow_status_id - 跟进状态元数据表id
     */
    public Integer getMetaFollowStatusId() {
        return metaFollowStatusId;
    }

    /**
     * 设置跟进状态元数据表id
     *
     * @param metaFollowStatusId 跟进状态元数据表id
     */
    public void setMetaFollowStatusId(Integer metaFollowStatusId) {
        this.metaFollowStatusId = metaFollowStatusId;
    }

    /**
     * 获取备注
     *
     * @return description - 备注
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置备注
     *
     * @param description 备注
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取创建时间
     *
     * @return creat_time - 创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置创建时间
     *
     * @param creatTime 创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}