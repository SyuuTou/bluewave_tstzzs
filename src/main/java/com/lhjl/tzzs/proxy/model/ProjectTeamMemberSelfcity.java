package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_team_member_selfcity")
public class ProjectTeamMemberSelfcity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 成员ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 自定义所在城市
     */
    private String city;

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
     * 获取自定义所在城市
     *
     * @return city - 自定义所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置自定义所在城市
     *
     * @param city 自定义所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }
}