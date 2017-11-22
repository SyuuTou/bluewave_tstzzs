package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "search_investment_record")
public class SearchInvestmentRecord {
    /**
     * 记录的id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 输入的字
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 用户的id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 搜索量
     */
    private Integer amout;

    /**
     * 获取记录的id
     *
     * @return id - 记录的id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置记录的id
     *
     * @param id 记录的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取输入的字
     *
     * @return short_name - 输入的字
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置输入的字
     *
     * @param shortName 输入的字
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取用户的id
     *
     * @return user_id - 用户的id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户的id
     *
     * @param userId 用户的id
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
     * 获取搜索量
     *
     * @return amout - 搜索量
     */
    public Integer getAmout() {
        return amout;
    }

    /**
     * 设置搜索量
     *
     * @param amout 搜索量
     */
    public void setAmout(Integer amout) {
        this.amout = amout;
    }
}