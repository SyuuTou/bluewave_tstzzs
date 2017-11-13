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
}