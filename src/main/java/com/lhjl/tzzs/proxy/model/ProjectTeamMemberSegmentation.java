package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_team_member_segmentation")
public class ProjectTeamMemberSegmentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 团队成员ID
     */
    @Column(name = "member_id")
    private Integer memberId;

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