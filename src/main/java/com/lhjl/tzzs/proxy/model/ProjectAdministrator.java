package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_administrator")
public class ProjectAdministrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 项目id
     */
    @Column(name = "projects_id")
    private Integer projectsId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 管理员类型，预留字段，0表示默认管理员
     */
    private Integer types;

    /**
     * 是否有效，0表示有效，1表示无效
     */
    private Integer yn;

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

    /**
     * 获取项目id
     *
     * @return projects_id - 项目id
     */
    public Integer getProjectsId() {
        return projectsId;
    }

    /**
     * 设置项目id
     *
     * @param projectsId 项目id
     */
    public void setProjectsId(Integer projectsId) {
        this.projectsId = projectsId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取管理员类型，预留字段，0表示默认管理员
     *
     * @return types - 管理员类型，预留字段，0表示默认管理员
     */
    public Integer getTypes() {
        return types;
    }

    /**
     * 设置管理员类型，预留字段，0表示默认管理员
     *
     * @param types 管理员类型，预留字段，0表示默认管理员
     */
    public void setTypes(Integer types) {
        this.types = types;
    }

    /**
     * 获取是否有效，0表示有效，1表示无效
     *
     * @return yn - 是否有效，0表示有效，1表示无效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效，0表示有效，1表示无效
     *
     * @param yn 是否有效，0表示有效，1表示无效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}