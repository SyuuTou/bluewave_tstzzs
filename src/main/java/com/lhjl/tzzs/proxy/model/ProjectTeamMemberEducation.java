package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_team_member_education")
public class ProjectTeamMemberEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目团队成员id
     */
    @Column(name = "project_team_member_id")
    private Integer projectTeamMemberId;

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