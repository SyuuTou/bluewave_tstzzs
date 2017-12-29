package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_team_member_education_b")
public class ProjectSendTeamMemberEducationB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目团队成员id
     */
    @Column(name = "ps_team_b_id")
    private Integer psTeamBId;

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