package com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto;

/**
 * Created by lanhaijulang on 2018/2/6.
 */
public class ProjectTeamIntroInputDto {
	
	/**
	 * 主体id
	 */
    private Integer projectId;
    /**
     * 团队成员介绍
     */
    private String teamIntroduction;
    /**
     * 主体类型
     */
    private Integer subjectType;
    
    public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

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

	@Override
	public String toString() {
		return "ProjectTeamIntroInputDto [projectId=" + projectId + ", teamIntroduction=" + teamIntroduction
				+ ", subjectType=" + subjectType + "]";
	}
    
}
