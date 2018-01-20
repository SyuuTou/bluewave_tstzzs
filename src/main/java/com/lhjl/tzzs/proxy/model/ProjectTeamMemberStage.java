package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_team_member_stage")
public class ProjectTeamMemberStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 团队成员ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 投资阶段ID
     */
    @Column(name = "stage_id")
    private Integer stageId;

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
     * 获取团队成员ID
     *
     * @return member_id - 团队成员ID
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 设置团队成员ID
     *
     * @param memberId 团队成员ID
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取投资阶段ID
     *
     * @return stage_id - 投资阶段ID
     */
    public Integer getStageId() {
        return stageId;
    }

    /**
     * 设置投资阶段ID
     *
     * @param stageId 投资阶段ID
     */
    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }
}