package com.lhjl.tzzs.proxy.dto.ProjectSendBAuditDto;

/**
 * Created by lanhaijulang on 2018/3/28.
 */
public class ProjectKernelInfoOutputDto {

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     *提交Id
     */
    private Integer projectSendId;

    /**
     * 项目Logo
     */
    private String logo;

    /**
     * 项目简称
     */
    private String shortName;

    /**
     * 项目所处轮次
     */
    private String stage;

    /**
     * 项目所处领域
     */
    private String segmentation;

    /**
     * 城市
     */
    private String city;

    /**
     * 一句话介绍
     */
    private String kernelDesc;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectSendId() {
        return projectSendId;
    }

    public void setProjectSendId(Integer projectSendId) {
        this.projectSendId = projectSendId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getKernelDesc() {
        return kernelDesc;
    }

    public void setKernelDesc(String kernelDesc) {
        this.kernelDesc = kernelDesc;
    }
}
