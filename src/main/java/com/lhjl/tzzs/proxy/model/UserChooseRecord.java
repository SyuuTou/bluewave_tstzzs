package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_choose_record")
public class UserChooseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 所选择数据
     */
    private String datas;

    /**
     * 选择场景
     */
    @Column(name = "scene_key")
    private String sceneKey;

    /**
     * 操作发生时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 行为类型，0表示浏览，1表示报名，2表示支付，3表示支付完成；
     */
    @Column(name = "action_type")
    private Integer actionType;

    /**
     * log类型，1表示精选服务报名类型操作，null为其他类型
     */
    @Column(name = "log_type")
    private Integer logType;

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
     * 获取所选择数据
     *
     * @return datas - 所选择数据
     */
    public String getDatas() {
        return datas;
    }

    /**
     * 设置所选择数据
     *
     * @param datas 所选择数据
     */
    public void setDatas(String datas) {
        this.datas = datas;
    }

    /**
     * 获取选择场景
     *
     * @return scene_key - 选择场景
     */
    public String getSceneKey() {
        return sceneKey;
    }

    /**
     * 设置选择场景
     *
     * @param sceneKey 选择场景
     */
    public void setSceneKey(String sceneKey) {
        this.sceneKey = sceneKey;
    }

    /**
     * 获取操作发生时间
     *
     * @return create_time - 操作发生时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置操作发生时间
     *
     * @param createTime 操作发生时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取行为类型，0表示浏览，1表示报名，2表示支付，3表示支付完成；
     *
     * @return action_type - 行为类型，0表示浏览，1表示报名，2表示支付，3表示支付完成；
     */
    public Integer getActionType() {
        return actionType;
    }

    /**
     * 设置行为类型，0表示浏览，1表示报名，2表示支付，3表示支付完成；
     *
     * @param actionType 行为类型，0表示浏览，1表示报名，2表示支付，3表示支付完成；
     */
    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    /**
     * 获取log类型，1表示精选服务报名类型操作，null为其他类型
     *
     * @return log_type - log类型，1表示精选服务报名类型操作，null为其他类型
     */
    public Integer getLogType() {
        return logType;
    }

    /**
     * 设置log类型，1表示精选服务报名类型操作，null为其他类型
     *
     * @param logType log类型，1表示精选服务报名类型操作，null为其他类型
     */
    public void setLogType(Integer logType) {
        this.logType = logType;
    }
}