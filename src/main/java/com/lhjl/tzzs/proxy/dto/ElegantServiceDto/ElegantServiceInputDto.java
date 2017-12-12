package com.lhjl.tzzs.proxy.dto.ElegantServiceDto;

import java.util.Date;

public class ElegantServiceInputDto {
    /**
     * 产品名称
     */
    private String serviceName;
    /**
     * 合作方
     */
    private String cooperationName;
    /**
     * 会员价（原价）
     */
    private String originalPrice;
    /**
     * vip会员价
     */
    private String vipPrice;
    /**
     * 限制单位
     */
    private String unit;
    /**
     * 封面图片
     */
    private String backgroundPicture;
    /**
     * 是否隐藏
     */
    private Integer onOff;
    /**
     * 是否推荐
     */
    private Integer recommendYn;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否切换原网页
     */
    private Integer webSwitch;
    /**
     * 简介
     */
    private String description;
    /**
     * 详细简介
     */
    private String detailDescription;
    /**
     * 上架时间
     */
    private String beginTime;
    /**
     * 下架时间
     */
    private String endTime;
    /**
     * 针对的身份类型
     */
    private String identityType;
    /**
     * 服务类型
     */
    private String serviceType;
    /**
     * 服务id
     */
    private Integer elegantServiceId;

    public Integer getElegantServiceId() {
        return elegantServiceId;
    }

    public void setElegantServiceId(Integer elegantServiceId) {
        this.elegantServiceId = elegantServiceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCooperationName() {
        return cooperationName;
    }

    public void setCooperationName(String cooperationName) {
        this.cooperationName = cooperationName;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(String vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }

    public Integer getOnOff() {
        return onOff;
    }

    public void setOnOff(Integer onOff) {
        this.onOff = onOff;
    }

    public Integer getRecommendYn() {
        return recommendYn;
    }

    public void setRecommendYn(Integer recommendYn) {
        this.recommendYn = recommendYn;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getWebSwitch() {
        return webSwitch;
    }

    public void setWebSwitch(Integer webSwitch) {
        this.webSwitch = webSwitch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
