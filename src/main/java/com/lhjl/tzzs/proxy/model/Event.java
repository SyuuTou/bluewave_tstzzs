package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 发起人id
     */
    @Column(name = "Initiator_id")
    private Integer initiatorId;

    /**
     * 事件类型表id
     */
    @Column(name = "event_type_id")
    private Integer eventTypeId;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 自定义id,现在的规则是：项目id+发起者id+本记录id
     */
    @Column(name = "customize_id")
    private String customizeId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取发起人id
     *
     * @return Initiator_id - 发起人id
     */
    public Integer getInitiatorId() {
        return initiatorId;
    }

    /**
     * 设置发起人id
     *
     * @param initiatorId 发起人id
     */
    public void setInitiatorId(Integer initiatorId) {
        this.initiatorId = initiatorId;
    }

    /**
     * 获取事件类型表id
     *
     * @return event_type_id - 事件类型表id
     */
    public Integer getEventTypeId() {
        return eventTypeId;
    }

    /**
     * 设置事件类型表id
     *
     * @param eventTypeId 事件类型表id
     */
    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
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
     * 获取自定义id,现在的规则是：项目id+发起者id+本记录id
     *
     * @return customize_id - 自定义id,现在的规则是：项目id+发起者id+本记录id
     */
    public String getCustomizeId() {
        return customizeId;
    }

    /**
     * 设置自定义id,现在的规则是：项目id+发起者id+本记录id
     *
     * @param customizeId 自定义id,现在的规则是：项目id+发起者id+本记录id
     */
    public void setCustomizeId(String customizeId) {
        this.customizeId = customizeId;
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
}