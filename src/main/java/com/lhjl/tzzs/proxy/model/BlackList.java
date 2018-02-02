package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "black_list")
public class BlackList {
    /**
     * 黑名单id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 添加人
     */
    private String creator;

    /**
     * 获取黑名单id
     *
     * @return id - 黑名单id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置黑名单id
     *
     * @param id 黑名单id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 获取添加人
     *
     * @return creator - 添加人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置添加人
     *
     * @param creator 添加人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
}