package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

public class Follow {
    /**
     * 关注id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关注人id
     */
    @Column(name = "users_id")
    private Integer usersId;

    /**
     * 被关注项目的id
     */
    @Column(name = "projects_id")
    private Integer projectsId;

    /**
     * 关注时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 关注状态：0：关注，1未关注
     */
    private Integer status;

    /**
     * 获取关注id
     *
     * @return ID - 关注id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置关注id
     *
     * @param id 关注id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取关注人id
     *
     * @return users_id - 关注人id
     */
    public Integer getUsersId() {
        return usersId;
    }

    /**
     * 设置关注人id
     *
     * @param usersId 关注人id
     */
    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    /**
     * 获取被关注项目的id
     *
     * @return projects_id - 被关注项目的id
     */
    public Integer getProjectsId() {
        return projectsId;
    }

    /**
     * 设置被关注项目的id
     *
     * @param projectsId 被关注项目的id
     */
    public void setProjectsId(Integer projectsId) {
        this.projectsId = projectsId;
    }

    /**
     * 获取关注时间
     *
     * @return create_time - 关注时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置关注时间
     *
     * @param createTime 关注时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取关注状态：0：关注，1未关注
     *
     * @return status - 关注状态：0：关注，1未关注
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置关注状态：0：关注，1未关注
     *
     * @param status 关注状态：0：关注，1未关注
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}