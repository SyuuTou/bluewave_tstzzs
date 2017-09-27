package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

public class Interview {
    /**
     * 约谈id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 约谈状态：0未读，1已读
     */
    private Integer status;

    /**
     * 项目的id
     */
    @Column(name = "projects_id")
    private Integer projectsId;

    /**
     * 发起人id
     */
    @Column(name = "users_id")
    private Integer usersId;

    /**
     * 约谈内容
     */
    private String desc;

    /**
     * 约谈时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取约谈id
     *
     * @return ID - 约谈id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置约谈id
     *
     * @param id 约谈id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取约谈状态：0未读，1已读
     *
     * @return status - 约谈状态：0未读，1已读
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置约谈状态：0未读，1已读
     *
     * @param status 约谈状态：0未读，1已读
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取项目的id
     *
     * @return projects_id - 项目的id
     */
    public Integer getProjectsId() {
        return projectsId;
    }

    /**
     * 设置项目的id
     *
     * @param projectsId 项目的id
     */
    public void setProjectsId(Integer projectsId) {
        this.projectsId = projectsId;
    }

    /**
     * 获取发起人id
     *
     * @return users_id - 发起人id
     */
    public Integer getUsersId() {
        return usersId;
    }

    /**
     * 设置发起人id
     *
     * @param usersId 发起人id
     */
    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    /**
     * 获取约谈内容
     *
     * @return desc - 约谈内容
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置约谈内容
     *
     * @param desc 约谈内容
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取约谈时间
     *
     * @return create_time - 约谈时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置约谈时间
     *
     * @param createTime 约谈时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}