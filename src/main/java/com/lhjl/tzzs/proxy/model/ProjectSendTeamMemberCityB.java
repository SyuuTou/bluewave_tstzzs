package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_team_member_city_b")
public class ProjectSendTeamMemberCityB {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目团队成员id
     */
    @Column(name = "ps_team_b_id")
    private Integer psTeamBId;

    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
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