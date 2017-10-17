package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_level_relation")
public class UserLevelRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 会员ID
     */
    @Column(name = "level_id")
    private Integer levelId;

    /**
     * 开通会员时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 会员到期时间
     */
    @Column(name = "end_time")
    private Date endTime;

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
     * 获取会员ID
     *
     * @return level_id - 会员ID
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * 设置会员ID
     *
     * @param levelId 会员ID
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * 获取开通会员时间
     *
     * @return begin_time - 开通会员时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置开通会员时间
     *
     * @param beginTime 开通会员时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取会员到期时间
     *
     * @return end_time - 会员到期时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置会员到期时间
     *
     * @param endTime 会员到期时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}