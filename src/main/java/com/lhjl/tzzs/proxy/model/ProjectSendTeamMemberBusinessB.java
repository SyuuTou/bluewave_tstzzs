package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_team_member_business_b")
public class ProjectSendTeamMemberBusinessB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目团队成员id
     */
    @Column(name = "ps_team_b_id")
    private Integer psTeamBId;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

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
     * 获取公司名称
     *
     * @return company_name - 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName 公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}