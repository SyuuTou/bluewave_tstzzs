package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_search_log")
public class UserSearchLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 搜索内容
     */
    @Column(name = "search_content")
    private String searchContent;

    /**
     * 搜索时间
     */
    @Column(name = "search_time")
    private Date searchTime;

    /**
     * 搜索量
     */
    private Integer amount;

    /**
     * 是否删除，1表示未删除，2表示已删除
     */
    private Integer yn;

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
     * 获取搜索内容
     *
     * @return search_content - 搜索内容
     */
    public String getSearchContent() {
        return searchContent;
    }

    /**
     * 设置搜索内容
     *
     * @param searchContent 搜索内容
     */
    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    /**
     * 获取搜索时间
     *
     * @return search_time - 搜索时间
     */
    public Date getSearchTime() {
        return searchTime;
    }

    /**
     * 设置搜索时间
     *
     * @param searchTime 搜索时间
     */
    public void setSearchTime(Date searchTime) {
        this.searchTime = searchTime;
    }

    /**
     * 获取搜索量
     *
     * @return amount - 搜索量
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置搜索量
     *
     * @param amount 搜索量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取是否删除，1表示未删除，2表示已删除
     *
     * @return yn - 是否删除，1表示未删除，2表示已删除
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否删除，1表示未删除，2表示已删除
     *
     * @param yn 是否删除，1表示未删除，2表示已删除
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}