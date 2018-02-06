package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_team_info")
public class ProjectTeamInfo {
    /**
     * 项目Id
     */
    @Id
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 团队简介（曹传桂）
     */
    @Column(name = "team_introduction")
    private String teamIntroduction;

    /**
     * 获取项目Id
     *
     * @return project_id - 项目Id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目Id
     *
     * @param projectId 项目Id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取团队简介（曹传桂）
     *
     * @return team_introduction - 团队简介（曹传桂）
     */
    public String getTeamIntroduction() {
        return teamIntroduction;
    }

    /**
     * 设置团队简介（曹传桂）
     *
     * @param teamIntroduction 团队简介（曹传桂）
     */
    public void setTeamIntroduction(String teamIntroduction) {
        this.teamIntroduction = teamIntroduction;
    }
}