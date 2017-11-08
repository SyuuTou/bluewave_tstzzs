package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_team_member_work")
public class ProjectSendTeamMemberWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目成员id
     */
    @Column(name = "project_send_team_member_id")
    private Integer projectSendTeamMemberId;

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
     * 获取提交项目成员id
     *
     * @return project_send_team_member_id - 提交项目成员id
     */
    public Integer getProjectSendTeamMemberId() {
        return projectSendTeamMemberId;
    }

    /**
     * 设置提交项目成员id
     *
     * @param projectSendTeamMemberId 提交项目成员id
     */
    public void setProjectSendTeamMemberId(Integer projectSendTeamMemberId) {
        this.projectSendTeamMemberId = projectSendTeamMemberId;
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