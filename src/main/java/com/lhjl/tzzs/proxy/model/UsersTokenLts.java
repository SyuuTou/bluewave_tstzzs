package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "users_token_lts")
public class UsersTokenLts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户token
     */
    private String token;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * 应用id
     */
    @Column(name = "meta_app_id")
    private Integer metaAppId;

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
     * 获取用户token
     *
     * @return token - 用户token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置用户token
     *
     * @param token 用户token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取注册时间
     *
     * @return register_time - 注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间
     *
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取应用id
     *
     * @return meta_app_id - 应用id
     */
    public Integer getMetaAppId() {
        return metaAppId;
    }

    /**
     * 设置应用id
     *
     * @param metaAppId 应用id
     */
    public void setMetaAppId(Integer metaAppId) {
        this.metaAppId = metaAppId;
    }
}