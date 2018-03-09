package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_team_member_stage_b")
public class ProjectSendTeamMemberStageB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目团队成员id
     */
    @Column(name = "ps_team_b_id")
    private Integer psTeamBId;

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