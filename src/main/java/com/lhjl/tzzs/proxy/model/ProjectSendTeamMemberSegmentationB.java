package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_team_member_segmentation_b")
public class ProjectSendTeamMemberSegmentationB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目团队成员id
     */
    @Column(name = "ps_team_b_id")
    private Integer psTeamBId;

    /**
     * 领域ID
     */
    @Column(name = "segmentation_id")
    private Integer segmentationId;

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
     * 获取领域ID
     *
     * @return segmentation_id - 领域ID
     */
    public Integer getSegmentationId() {
        return segmentationId;
    }

    /**
     * 设置领域ID
     *
     * @param segmentationId 领域ID
     */
    public void setSegmentationId(Integer segmentationId) {
        this.segmentationId = segmentationId;
    }
}