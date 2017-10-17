package com.lhjl.tzzs.proxy.dto;

/**
 * 用户操作场景请求对象
 * Created by 蓝海巨浪 on 2017/10/17.
 */
public class ActionDto {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 场景key
     */
    private String sceneKey;

    /**
     * 会员等级ID
     */
    private Integer levelId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSceneKey() {
        return sceneKey;
    }

    public void setSceneKey(String sceneKey) {
        this.sceneKey = sceneKey;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
}
