package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_projects")
public class UserProjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 提交的项目ID
     */
    @Column(name = "send_project_id")
    private Integer sendProjectId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否删除状态，0:未删除，1:已删除
     */
    @Column(name = "isDel")
    private Integer isdel;

    /**
     * 数据是否有效，0:无效，1:为有效
     */
    private Integer yn;

    /**
     * 来源平台ID，1:平台库，2:采集库
     */
    @Column(name = "source_platform_id")
    private Integer sourcePlatformId;

    /**
     * 小程序动作：1:小程序提交项目， 2:关注项目
     */
    @Column(name = "action_id")
    private Integer actionId;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取提交的项目ID
     *
     * @return send_project_id - 提交的项目ID
     */
    public Integer getSendProjectId() {
        return sendProjectId;
    }

    /**
     * 设置提交的项目ID
     *
     * @param sendProjectId 提交的项目ID
     */
    public void setSendProjectId(Integer sendProjectId) {
        this.sendProjectId = sendProjectId;
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

    /**
     * 获取是否删除状态，0:未删除，1:已删除
     *
     * @return isDel - 是否删除状态，0:未删除，1:已删除
     */
    public Integer getIsdel() {
        return isdel;
    }

    /**
     * 设置是否删除状态，0:未删除，1:已删除
     *
     * @param isdel 是否删除状态，0:未删除，1:已删除
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    /**
     * 获取数据是否有效，0:无效，1:为有效
     *
     * @return yn - 数据是否有效，0:无效，1:为有效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置数据是否有效，0:无效，1:为有效
     *
     * @param yn 数据是否有效，0:无效，1:为有效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取来源平台ID，1:平台库，2:采集库
     *
     * @return source_platform_id - 来源平台ID，1:平台库，2:采集库
     */
    public Integer getSourcePlatformId() {
        return sourcePlatformId;
    }

    /**
     * 设置来源平台ID，1:平台库，2:采集库
     *
     * @param sourcePlatformId 来源平台ID，1:平台库，2:采集库
     */
    public void setSourcePlatformId(Integer sourcePlatformId) {
        this.sourcePlatformId = sourcePlatformId;
    }

    /**
     * 获取小程序动作：1:小程序提交项目， 2:关注项目
     *
     * @return action_id - 小程序动作：1:小程序提交项目， 2:关注项目
     */
    public Integer getActionId() {
        return actionId;
    }

    /**
     * 设置小程序动作：1:小程序提交项目， 2:关注项目
     *
     * @param actionId 小程序动作：1:小程序提交项目， 2:关注项目
     */
    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }
}