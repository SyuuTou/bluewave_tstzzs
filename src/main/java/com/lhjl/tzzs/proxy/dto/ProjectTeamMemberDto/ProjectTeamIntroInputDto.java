package com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto;

/**
 * Created by lanhaijulang on 2018/2/6.
 */
public class ProjectTeamIntroInputDto {

    private Integer projectId;

    private String teamIntroduction;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTeamIntroduction() {
        return teamIntroduction;
    }

    public void setTeamIntroduction(String teamIntroduction) {
        this.teamIntroduction = teamIntroduction;
    }
}
