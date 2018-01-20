package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_team_member_selfteam")
public class ProjectTeamMemberSelfteam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 成员ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 团队名称
     */
    @Column(name = "team_name")
    private String teamName;

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
     * 获取成员ID
     *
     * @return member_id - 成员ID
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 设置成员ID
     *
     * @param memberId 成员ID
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取团队名称
     *
     * @return team_name - 团队名称
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * 设置团队名称
     *
     * @param teamName 团队名称
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}