package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_levelchange_record")
public class UserLevelchangeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 升级会员ID
     */
    @Column(name = "level_id")
    private Integer levelId;

    /**
     * 记录时间
     */
    private Date record;

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
     * 获取升级会员ID
     *
     * @return level_id - 升级会员ID
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * 设置升级会员ID
     *
     * @param levelId 升级会员ID
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * 获取记录时间
     *
     * @return record - 记录时间
     */
    public Date getRecord() {
        return record;
    }

    /**
     * 设置记录时间
     *
     * @param record 记录时间
     */
    public void setRecord(Date record) {
        this.record = record;
    }
}