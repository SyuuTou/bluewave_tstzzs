package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "miniapp_formid")
public class MiniappFormid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "form_id")
    private String formId;

    @Column(name = "scene_key")
    private String sceneKey;

    @Column(name = "create_time")
    private Date createTime;

    private String token;

    /**
     * 使用状态，0表示未使用，1表示已使用，2表示正在用；使用时用最老的那个formid;
     */
    private Integer yn;

    /**
     * 使用时间
     */
    @Column(name = "use_time")
    private Date useTime;

    /**
     * formid解除锁定的时间
     */
    @Column(name = "relieve_time")
    private Date relieveTime;

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
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return form_id
     */
    public String getFormId() {
        return formId;
    }

    /**
     * @param formId
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    /**
     * @return scene_key
     */
    public String getSceneKey() {
        return sceneKey;
    }

    /**
     * @param sceneKey
     */
    public void setSceneKey(String sceneKey) {
        this.sceneKey = sceneKey;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取使用状态，0表示未使用，1表示已使用，2表示正在用；使用时用最老的那个formid;
     *
     * @return yn - 使用状态，0表示未使用，1表示已使用，2表示正在用；使用时用最老的那个formid;
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置使用状态，0表示未使用，1表示已使用，2表示正在用；使用时用最老的那个formid;
     *
     * @param yn 使用状态，0表示未使用，1表示已使用，2表示正在用；使用时用最老的那个formid;
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取使用时间
     *
     * @return use_time - 使用时间
     */
    public Date getUseTime() {
        return useTime;
    }

    /**
     * 设置使用时间
     *
     * @param useTime 使用时间
     */
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    /**
     * 获取formid解除锁定的时间
     *
     * @return relieve_time - formid解除锁定的时间
     */
    public Date getRelieveTime() {
        return relieveTime;
    }

    /**
     * 设置formid解除锁定的时间
     *
     * @param relieveTime formid解除锁定的时间
     */
    public void setRelieveTime(Date relieveTime) {
        this.relieveTime = relieveTime;
    }
}