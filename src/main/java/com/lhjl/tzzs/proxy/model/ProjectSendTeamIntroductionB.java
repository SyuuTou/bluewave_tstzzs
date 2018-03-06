package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_send_team_introduction_b")
public class ProjectSendTeamIntroductionB {
    /**
     * 提交项目表project_send_b的主键id
     */
    @Id
    @Column(name = "project_send_b_id")
    private Integer projectSendBId;

    /**
     * 提交项目的团队介绍
     */
    @Column(name = "team_introduction")
    private String teamIntroduction;

    /**
     * 创建者token
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取提交项目表project_send_b的主键id
     *
     * @return project_send_b_id - 提交项目表project_send_b的主键id
     */
    public Integer getProjectSendBId() {
        return projectSendBId;
    }

    /**
     * 设置提交项目表project_send_b的主键id
     *
     * @param projectSendBId 提交项目表project_send_b的主键id
     */
    public void setProjectSendBId(Integer projectSendBId) {
        this.projectSendBId = projectSendBId;
    }

    /**
     * 获取提交项目的团队介绍
     *
     * @return team_introduction - 提交项目的团队介绍
     */
    public String getTeamIntroduction() {
        return teamIntroduction;
    }

    /**
     * 设置提交项目的团队介绍
     *
     * @param teamIntroduction 提交项目的团队介绍
     */
    public void setTeamIntroduction(String teamIntroduction) {
        this.teamIntroduction = teamIntroduction;
    }

    /**
     * 获取创建者token
     *
     * @return creator - 创建者token
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者token
     *
     * @param creator 创建者token
     */
    public void setCreator(String creator) {
        this.creator = creator;
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