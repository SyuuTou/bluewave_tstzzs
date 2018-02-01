package com.lhjl.tzzs.proxy.dto;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
public class CollectProjectAuditCompanyIntroDto {

    private Integer projectId;

    private String companyIntroduction;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction;
    }
}
