package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_team_member_work_b")
public class ProjectSendTeamMemberWorkB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目团队成员id
     */
    @Column(name = "ps_team_b_id")
    private Integer psTeamBId;

    /**
     * 工作经历
     */
    @Column(name = "work_experience")
    private String workExperience;

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
     * 获取提交项目团队成员id
     *
     * @return ps_team_b_id - 提交项目团队成员id
     */
    public Integer getPsTeamBId() {
        return psTeamBId;
    }

    /**
     * 设置提交项目团队成员id
     *
     * @param psTeamBId 提交项目团队成员id
     */
    public void setPsTeamBId(Integer psTeamBId) {
        this.psTeamBId = psTeamBId;
    }

    /**
     * 获取工作经历
     *
     * @return work_experience - 工作经历
     */
    public String getWorkExperience() {
        return workExperience;
    }

    /**
     * 设置工作经历
     *
     * @param workExperience 工作经历
     */
    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}