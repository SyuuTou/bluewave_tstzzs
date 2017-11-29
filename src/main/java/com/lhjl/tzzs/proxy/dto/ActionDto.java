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

    /**
     * 项目ID
     * @return
     */
    private Integer projectsId;

    /**
     * 机构ID（多个）
     */
    private String investmentIds;

    /**
     * 融资阶段
     */
    private String roundName;

    /**
     * 所属领域
     */
    private String industryName;

    /**
     * 所在城市
     */
    private String cityName;

    /**
     * 工作背景
     */
    private String educationName;

    /** 赠送周期 */
    private Integer period;
    /**
     * 毕业院校
     */
    private String workName;

    private String presentedType;

    /**
     * 备用字段，辅助场景使用
     */
    private Integer reserveField;

    public Integer getReserveField() {
        return reserveField;
    }

    public void setReserveField(Integer reserveField) {
        this.reserveField = reserveField;
    }

    public String getPresentedType() {
        return presentedType;
    }

    public void setPresentedType(String presentedType) {
        this.presentedType = presentedType;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

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

    public Integer getProjectsId() {
        return projectsId;
    }

    public void setProjectsId(Integer projectsId) {
        this.projectsId = projectsId;
    }

    public String getInvestmentIds() {
        return investmentIds;
    }

    public void setInvestmentIds(String investmentIds) {
        this.investmentIds = investmentIds;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }
}
