package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "users_weixin_lts")
public class UsersWeixinLts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户表id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 微信昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 手机号码
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * unionid
     */
    @Column(name = "union_id")
    private String unionId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * openid
     */
    @Column(name = "openId")
    private String openid;

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
     * 获取用户表id
     *
     * @return user_id - 用户表id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户表id
     *
     * @param userId 用户表id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取微信昵称
     *
     * @return nick_name - 微信昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置微信昵称
     *
     * @param nickName 微信昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取手机号码
     *
     * @return phone_number - 手机号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置手机号码
     *
     * @param phoneNumber 手机号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取unionid
     *
     * @return union_id - unionid
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * 设置unionid
     *
     * @param unionId unionid
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId;
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
     * 获取openid
     *
     * @return openId - openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置openid
     *
     * @param openid openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
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