package com.lhjl.tzzs.proxy.dto;

import java.util.List;

/**
 * Created by 蓝海巨浪 on 2017/10/16.
 */
public class UserLevelDto {
    /**
     * 会员ID
     */
    private Integer id;
    /**
     * 会员名称
     */
    private String name;
    /**
     * 会员价格
     */
    private Integer amount;

    /**
     * 折扣价格
     */
    private Integer dicount;

    /**
     * 实际价格
     */
    private Integer actualPrice;

    /**
     * 会员描述
     */
    private List<String> descs;

    /**
     * 当前用户是否拥有该会员(0没有，1拥有)
     */
    private String belong;

    /**
     * 原价
     * @return
     */
    private Integer originalCost;

    /**
     * 场景key
     */
    private String sceneKey;

    /**
     * 保存的当前会员价格
     */
    private Integer moneyId;

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<String> getDescs() {
        return descs;
    }

    public void setDescs(List<String> descs) {
        this.descs = descs;
    }

    public Integer getDicount() {
        return dicount;
    }

    public void setDicount(Integer dicount) {
        this.dicount = dicount;
    }

    public Integer getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(Integer originalCost) {
        this.originalCost = originalCost;
    }

    public String getSceneKey() {
        return sceneKey;
    }

    public void setSceneKey(String sceneKey) {
        this.sceneKey = sceneKey;
    }

    public Integer getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(Integer moneyId) {
        this.moneyId = moneyId;
    }
}
