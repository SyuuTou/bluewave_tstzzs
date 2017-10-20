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
    @Column(name = "datas_id")
    private String datasId;

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
     * @return datas_id - 所选择数据
     */
    public String getDatasId() {
        return datasId;
    }

    /**
     * 设置所选择数据
     *
     * @param datasId 所选择数据
     */
    public void setDatasId(String datasId) {
        this.datasId = datasId;
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
}