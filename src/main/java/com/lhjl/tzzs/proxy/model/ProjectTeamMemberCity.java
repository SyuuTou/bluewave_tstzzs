package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_team_member_city")
public class ProjectTeamMemberCity {
    /**
     * 数据库ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 团队成员ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 获取数据库ID
     *
     * @return id - 数据库ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置数据库ID
     *
     * @param id 数据库ID
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
     * 获取城市名称
     *
     * @return city_name - 城市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置城市名称
     *
     * @param cityName 城市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}