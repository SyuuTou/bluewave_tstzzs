package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "user_scene")
public class UserScene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 场景key
     */
    @Column(name = "scene_key")
    private String sceneKey;

    /**
     * 场景消费提示:0提示,1不再提示
     */
    private Integer flag;

    /**
     * 是否有效:0有效,1无效
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
     * 获取场景key
     *
     * @return scene_key - 场景key
     */
    public String getSceneKey() {
        return sceneKey;
    }

    /**
     * 设置场景key
     *
     * @param sceneKey 场景key
     */
    public void setSceneKey(String sceneKey) {
        this.sceneKey = sceneKey;
    }

    /**
     * 获取场景消费提示:0提示,1不再提示
     *
     * @return flag - 场景消费提示:0提示,1不再提示
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置场景消费提示:0提示,1不再提示
     *
     * @param flag 场景消费提示:0提示,1不再提示
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 获取是否有效:0有效,1无效
     *
     * @return yn - 是否有效:0有效,1无效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效:0有效,1无效
     *
     * @param yn 是否有效:0有效,1无效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}