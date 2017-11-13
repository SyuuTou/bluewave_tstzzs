package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "project_send_team_member")
public class ProjectSendTeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目表id
     */
    @Column(name = "project_send_logs_id")
    private Integer projectSendLogsId;

    /**
     * 成员姓名
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 成员职务
     */
    @Column(name = "member_duties")
    private String memberDuties;

    /**
     * 股份占比
     */
    @Column(name = "share_ratio")
    private BigDecimal shareRatio;

    /**
     * 成员简介
     */
    @Column(name = "member_desc")
    private String memberDesc;

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
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取提交项目表id
     *
     * @return project_send_logs_id - 提交项目表id
     */
    public Integer getProjectSendLogsId() {
        return projectSendLogsId;
    }

    /**
     * 设置提交项目表id
     *
     * @param projectSendLogsId 提交项目表id
     */
    public void setProjectSendLogsId(Integer projectSendLogsId) {
        this.projectSendLogsId = projectSendLogsId;
    }

    /**
     * 获取成员姓名
     *
     * @return member_name - 成员姓名
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 设置成员姓名
     *
     * @param memberName 成员姓名
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取成员职务
     *
     * @return member_duties - 成员职务
     */
    public String getMemberDuties() {
        return memberDuties;
    }

    /**
     * 设置成员职务
     *
     * @param memberDuties 成员职务
     */
    public void setMemberDuties(String memberDuties) {
        this.memberDuties = memberDuties;
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
     * @return member_desc - 成员简介
     */
    public String getMemberDesc() {
        return memberDesc;
    }

    /**
     * 设置成员简介
     *
     * @param memberDesc 成员简介
     */
    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
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

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}