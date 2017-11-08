package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "project_team_member")
public class ProjectTeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 成员姓名
     */
    @Column(name = "mumber_name")
    private String mumberName;

    /**
     * 成员职务
     */
    @Column(name = "mumber_duties")
    private String mumberDuties;

    /**
     * 股份占比
     */
    @Column(name = "share_ratio")
    private BigDecimal shareRatio;

    /**
     * 成员简介
     */
    @Column(name = "mumber_desc")
    private String mumberDesc;

    /**
     * 删除状态，0表示未删除，1表示已删除
     */
    private Integer yn;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * @return project_id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取成员姓名
     *
     * @return mumber_name - 成员姓名
     */
    public String getMumberName() {
        return mumberName;
    }

    /**
     * 设置成员姓名
     *
     * @param mumberName 成员姓名
     */
    public void setMumberName(String mumberName) {
        this.mumberName = mumberName;
    }

    /**
     * 获取成员职务
     *
     * @return mumber_duties - 成员职务
     */
    public String getMumberDuties() {
        return mumberDuties;
    }

    /**
     * 设置成员职务
     *
     * @param mumberDuties 成员职务
     */
    public void setMumberDuties(String mumberDuties) {
        this.mumberDuties = mumberDuties;
    }

    /**
     * 获取股份占比
     *
     * @return share_ratio - 股份占比
     */
    public BigDecimal getShareRatio() {
        return shareRatio;
    }

    /**
     * 设置股份占比
     *
     * @param shareRatio 股份占比
     */
    public void setShareRatio(BigDecimal shareRatio) {
        this.shareRatio = shareRatio;
    }

    /**
     * 获取成员简介
     *
     * @return mumber_desc - 成员简介
     */
    public String getMumberDesc() {
        return mumberDesc;
    }

    /**
     * 设置成员简介
     *
     * @param mumberDesc 成员简介
     */
    public void setMumberDesc(String mumberDesc) {
        this.mumberDesc = mumberDesc;
    }

    /**
     * 获取删除状态，0表示未删除，1表示已删除
     *
     * @return yn - 删除状态，0表示未删除，1表示已删除
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置删除状态，0表示未删除，1表示已删除
     *
     * @param yn 删除状态，0表示未删除，1表示已删除
     */
    public void setYn(Integer yn) {
        this.yn = yn;
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
}