package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user_integrals")
public class UserIntegrals {
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
     * 积分数量
     */
    @Column(name = "integral_num")
    private BigDecimal integralNum;

    /**
     * 有效开始时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 失效时间
     */
    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 累计消费的积分
     */
    @Column(name = "consume_num")
    private BigDecimal consumeNum;

    /**
     * 消费时间
     */
    @Column(name = "consume_time")
    private Date consumeTime;

    @Column(name = "app_id")
    private Integer appId;


    private Integer currency;


    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

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
     * 获取有效开始时间
     *
     * @return begin_time - 有效开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置有效开始时间
     *
     * @param beginTime 有效开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取失效时间
     *
     * @return end_time - 失效时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置失效时间
     *
     * @param endTime 失效时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getIntegralNum() {
        return integralNum;
    }

    public void setIntegralNum(BigDecimal integralNum) {
        this.integralNum = integralNum;
    }

    public BigDecimal getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(BigDecimal consumeNum) {
        this.consumeNum = consumeNum;
    }

    /**
     * 获取消费时间
     *
     * @return consume_time - 消费时间
     */
    public Date getConsumeTime() {
        return consumeTime;
    }

    /**
     * 设置消费时间
     *
     * @param consumeTime 消费时间
     */
    public void setConsumeTime(Date consumeTime) {
        this.consumeTime = consumeTime;
    }
}