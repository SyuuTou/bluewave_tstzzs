package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_team_member_work")
public class ProjectTeamMemberWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目团队成员id
     */
    @Column(name = "project_team_member_id")
    private Integer projectTeamMemberId;

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
     * 获取项目团队成员id
     *
     * @return project_team_member_id - 项目团队成员id
     */
    public Integer getProjectTeamMemberId() {
        return projectTeamMemberId;
    }

    /**
     * 设置项目团队成员id
     *
     * @param projectTeamMemberId 项目团队成员id
     */
    public void setProjectTeamMemberId(Integer projectTeamMemberId) {
        this.projectTeamMemberId = projectTeamMemberId;
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