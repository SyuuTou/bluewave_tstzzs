package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "elegant_service_participate")
public class ElegantServiceParticipate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 悬赏ID

     */
    @Column(name = "elegant_service_id")
    private Integer elegantServiceId;

    /**
     * 参与人token
     */
    private String token;

    /**
     * 状态，0：未开始，1: 正在进行中，2: 完成
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 完成时间
     */
    @Column(name = "completion_time")
    private Date completionTime;

    @Column(name = "appId")
    private Integer appid;

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
     * 获取悬赏ID

     *
     * @return elegant_service_id - 悬赏ID

     */
    public Integer getElegantServiceId() {
        return elegantServiceId;
    }

    /**
     * 设置悬赏ID

     *
     * @param elegantServiceId 悬赏ID

     */
    public void setElegantServiceId(Integer elegantServiceId) {
        this.elegantServiceId = elegantServiceId;
    }

    /**
     * 获取参与人token
     *
     * @return token - 参与人token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置参与人token
     *
     * @param token 参与人token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取状态，0：未开始，1: 正在进行中，2: 完成
     *
     * @return status - 状态，0：未开始，1: 正在进行中，2: 完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态，0：未开始，1: 正在进行中，2: 完成
     *
     * @param status 状态，0：未开始，1: 正在进行中，2: 完成
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取完成时间
     *
     * @return completion_time - 完成时间
     */
    public Date getCompletionTime() {
        return completionTime;
    }

    /**
     * 设置完成时间
     *
     * @param completionTime 完成时间
     */
    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    /**
     * @return appId
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * @param appid
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }
}