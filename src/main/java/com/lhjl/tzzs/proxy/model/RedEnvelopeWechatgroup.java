package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "red_envelope_wechatgroup")
public class RedEnvelopeWechatgroup {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 红包ID
     */
    @Column(name = "red_envelope_id")
    private Long redEnvelopeId;

    /**
     * 群ID
     */
    @Column(name = "wechat_group_id")
    private String wechatGroupId;

    /**
     * 唯一key
     */
    @Column(name = "union_key")
    private String unionKey;

    /**
     * 用户Token
     */
    private String token;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "app_id")
    private Integer appId;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取红包ID
     *
     * @return red_envelope_id - 红包ID
     */
    public Long getRedEnvelopeId() {
        return redEnvelopeId;
    }

    /**
     * 设置红包ID
     *
     * @param redEnvelopeId 红包ID
     */
    public void setRedEnvelopeId(Long redEnvelopeId) {
        this.redEnvelopeId = redEnvelopeId;
    }

    /**
     * 获取群ID
     *
     * @return wechat_group_id - 群ID
     */
    public String getWechatGroupId() {
        return wechatGroupId;
    }

    /**
     * 设置群ID
     *
     * @param wechatGroupId 群ID
     */
    public void setWechatGroupId(String wechatGroupId) {
        this.wechatGroupId = wechatGroupId;
    }

    /**
     * 获取唯一key
     *
     * @return union_key - 唯一key
     */
    public String getUnionKey() {
        return unionKey;
    }

    /**
     * 设置唯一key
     *
     * @param unionKey 唯一key
     */
    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }

    /**
     * 获取用户Token
     *
     * @return token - 用户Token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置用户Token
     *
     * @param token 用户Token
     */
    public void setToken(String token) {
        this.token = token;
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
     * @return app_id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }
}