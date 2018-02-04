package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_integral_consume_datas")
public class UserIntegralConsumeDatas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 场景key
     */
    @Column(name = "scene_key")
    private String sceneKey;

    /**
     * 数据ID
     */
    @Column(name = "datas_id")
    private String datasId;

    /**
     * 消费日期
     */
    @Column(name = "consume_date")
    private Date consumeDate;

    /**
     * 用户金币消费记录ID
     */
    @Column(name = "user_integral_consume_id")
    private Integer userIntegralConsumeId;

    @Column(name = "app_id")
    private Integer appId;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }
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
     * 获取场景key
     *
     * @return scene_key - 场景key
     */
    public String getSceneKey() {
        return sceneKey;
    }

    /**
     * 设置场景key
     *
     * @param sceneKey 场景key
     */
    public void setSceneKey(String sceneKey) {
        this.sceneKey = sceneKey;
    }

    /**
     * 获取数据ID
     *
     * @return datas_id - 数据ID
     */
    public String getDatasId() {
        return datasId;
    }

    /**
     * 设置数据ID
     *
     * @param datasId 数据ID
     */
    public void setDatasId(String datasId) {
        this.datasId = datasId;
    }

    /**
     * 获取消费日期
     *
     * @return consume_date - 消费日期
     */
    public Date getConsumeDate() {
        return consumeDate;
    }

    /**
     * 设置消费日期
     *
     * @param consumeDate 消费日期
     */
    public void setConsumeDate(Date consumeDate) {
        this.consumeDate = consumeDate;
    }

    /**
     * 获取用户金币消费记录ID
     *
     * @return user_integral_consume_id - 用户金币消费记录ID
     */
    public Integer getUserIntegralConsumeId() {
        return userIntegralConsumeId;
    }

    /**
     * 设置用户金币消费记录ID
     *
     * @param userIntegralConsumeId 用户金币消费记录ID
     */
    public void setUserIntegralConsumeId(Integer userIntegralConsumeId) {
        this.userIntegralConsumeId = userIntegralConsumeId;
    }
}