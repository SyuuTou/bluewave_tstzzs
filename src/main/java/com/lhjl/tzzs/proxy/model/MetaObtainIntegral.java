package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "meta_obtain_integral")
public class MetaObtainIntegral {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 场景Id
     */
    @Column(name = "scene_id")
    private Integer sceneId;

    /**
     * 用户级别
     */
    @Column(name = "user_level")
    private Integer userLevel;

    /**
     * 积分，
     */
    private Integer integral;

    /**
     * 周期
     */
    private Integer period;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效：0：有效，1:无效删除
     */
    private Integer yn;

    /**
     * @return ID
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
     * 获取场景Id
     *
     * @return scene_id - 场景Id
     */
    public Integer getSceneId() {
        return sceneId;
    }

    /**
     * 设置场景Id
     *
     * @param sceneId 场景Id
     */
    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * 获取用户级别
     *
     * @return user_level - 用户级别
     */
    public Integer getUserLevel() {
        return userLevel;
    }

    /**
     * 设置用户级别
     *
     * @param userLevel 用户级别
     */
    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * 获取积分，
     *
     * @return integral - 积分，
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * 设置积分，
     *
     * @param integral 积分，
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取周期
     *
     * @return period - 周期
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置周期
     *
     * @param period 周期
     */
    public void setPeriod(Integer period) {
        this.period = period;
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
     * 获取是否有效：0：有效，1:无效删除
     *
     * @return yn - 是否有效：0：有效，1:无效删除
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效：0：有效，1:无效删除
     *
     * @param yn 是否有效：0：有效，1:无效删除
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}