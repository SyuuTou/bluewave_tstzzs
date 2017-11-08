package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_team_member_education")
public class ProjectSendTeamMemberEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目成员id
     */
    @Column(name = "project_send_team_member_id")
    private Integer projectSendTeamMemberId;

    /**
     * 教育经历
     */
    @Column(name = "education_experience")
    private String educationExperience;

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
     * 获取教育经历
     *
     * @return education_experience - 教育经历
     */
    public String getEducationExperience() {
        return educationExperience;
    }

    /**
     * 设置教育经历
     *
     * @param educationExperience 教育经历
     */
    public void setEducationExperience(String educationExperience) {
        this.educationExperience = educationExperience;
    }
}