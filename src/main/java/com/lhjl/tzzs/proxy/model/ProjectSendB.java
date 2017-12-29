package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_send_b")
public class ProjectSendB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目logo
     */
    @Column(name = "project_logo")
    private String projectLogo;

    /**
     * 项目简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 项目全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 一句话介绍
     */
    @Column(name = "kernel_desc")
    private String kernelDesc;

    /**
     * 项目亮点
     */
    @Column(name = "project_investment_highlights")
    private String projectInvestmentHighlights;

    /**
     * 项目简介
     */
    private String commet;

    /**
     * 官网
     */
    private String url;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 创建者id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 预生成id
     */
    @Column(name = "prepare_id")
    private Integer prepareId;

    /**
     * 编辑状态，0表示未编辑完成，1表示编辑完成了
     */
    @Column(name = "edit_status")
    private Integer editStatus;

    /**
     * 应用id
     */
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
     * 获取项目logo
     *
     * @return project_logo - 项目logo
     */
    public String getProjectLogo() {
        return projectLogo;
    }

    /**
     * 设置项目logo
     *
     * @param projectLogo 项目logo
     */
    public void setProjectLogo(String projectLogo) {
        this.projectLogo = projectLogo;
    }

    /**
     * 获取项目简称
     *
     * @return short_name - 项目简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置项目简称
     *
     * @param shortName 项目简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取项目全称
     *
     * @return full_name - 项目全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置项目全称
     *
     * @param fullName 项目全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取一句话介绍
     *
     * @return kernel_desc - 一句话介绍
     */
    public String getKernelDesc() {
        return kernelDesc;
    }

    /**
     * 设置一句话介绍
     *
     * @param kernelDesc 一句话介绍
     */
    public void setKernelDesc(String kernelDesc) {
        this.kernelDesc = kernelDesc;
    }

    /**
     * 获取项目亮点
     *
     * @return project_investment_highlights - 项目亮点
     */
    public String getProjectInvestmentHighlights() {
        return projectInvestmentHighlights;
    }

    /**
     * 设置项目亮点
     *
     * @param projectInvestmentHighlights 项目亮点
     */
    public void setProjectInvestmentHighlights(String projectInvestmentHighlights) {
        this.projectInvestmentHighlights = projectInvestmentHighlights;
    }

    /**
     * 获取项目简介
     *
     * @return commet - 项目简介
     */
    public String getCommet() {
        return commet;
    }

    /**
     * 设置项目简介
     *
     * @param commet 项目简介
     */
    public void setCommet(String commet) {
        this.commet = commet;
    }

    /**
     * 获取官网
     *
     * @return url - 官网
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置官网
     *
     * @param url 官网
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取所在城市
     *
     * @return city - 所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置所在城市
     *
     * @param city 所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取创建者id
     *
     * @return user_id - 创建者id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置创建者id
     *
     * @param userId 创建者id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 获取预生成id
     *
     * @return prepare_id - 预生成id
     */
    public Integer getPrepareId() {
        return prepareId;
    }

    /**
     * 设置预生成id
     *
     * @param prepareId 预生成id
     */
    public void setPrepareId(Integer prepareId) {
        this.prepareId = prepareId;
    }

    /**
     * 获取编辑状态，0表示未编辑完成，1表示编辑完成了
     *
     * @return edit_status - 编辑状态，0表示未编辑完成，1表示编辑完成了
     */
    public Integer getEditStatus() {
        return editStatus;
    }

    /**
     * 设置编辑状态，0表示未编辑完成，1表示编辑完成了
     *
     * @param editStatus 编辑状态，0表示未编辑完成，1表示编辑完成了
     */
    public void setEditStatus(Integer editStatus) {
        this.editStatus = editStatus;
    }

    /**
     * 获取应用id
     *
     * @return appid - 应用id
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * 设置应用id
     *
     * @param appid 应用id
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }
}