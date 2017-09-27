package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

public class Founders {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String position;

    private String introduction;

    /**
     * 创始团队教育背景（重点本科及以上院校）
     */
    @Column(name = "educational_background_desc")
    private String educationalBackgroundDesc;

    /**
     * 创始团队工作背景（知名企业经历）
     */
    @Column(name = "working_background_desc")
    private String workingBackgroundDesc;

    /**
     * 创业经历描述
     */
    @Column(name = "entrepreneurial_experience")
    private String entrepreneurialExperience;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效(0有效，1无效)
     */
    private Integer yn;

    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * @return ID
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取创始团队教育背景（重点本科及以上院校）
     *
     * @return educational_background_desc - 创始团队教育背景（重点本科及以上院校）
     */
    public String getEducationalBackgroundDesc() {
        return educationalBackgroundDesc;
    }

    /**
     * 设置创始团队教育背景（重点本科及以上院校）
     *
     * @param educationalBackgroundDesc 创始团队教育背景（重点本科及以上院校）
     */
    public void setEducationalBackgroundDesc(String educationalBackgroundDesc) {
        this.educationalBackgroundDesc = educationalBackgroundDesc;
    }

    /**
     * 获取创始团队工作背景（知名企业经历）
     *
     * @return working_background_desc - 创始团队工作背景（知名企业经历）
     */
    public String getWorkingBackgroundDesc() {
        return workingBackgroundDesc;
    }

    /**
     * 设置创始团队工作背景（知名企业经历）
     *
     * @param workingBackgroundDesc 创始团队工作背景（知名企业经历）
     */
    public void setWorkingBackgroundDesc(String workingBackgroundDesc) {
        this.workingBackgroundDesc = workingBackgroundDesc;
    }

    /**
     * 获取创业经历描述
     *
     * @return entrepreneurial_experience - 创业经历描述
     */
    public String getEntrepreneurialExperience() {
        return entrepreneurialExperience;
    }

    /**
     * 设置创业经历描述
     *
     * @param entrepreneurialExperience 创业经历描述
     */
    public void setEntrepreneurialExperience(String entrepreneurialExperience) {
        this.entrepreneurialExperience = entrepreneurialExperience;
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
     * 获取是否有效(0有效，1无效)
     *
     * @return yn - 是否有效(0有效，1无效)
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效(0有效，1无效)
     *
     * @param yn 是否有效(0有效，1无效)
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取项目ID
     *
     * @return project_id - 项目ID
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目ID
     *
     * @param projectId 项目ID
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}