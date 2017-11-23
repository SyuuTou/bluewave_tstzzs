package com.lhjl.tzzs.proxy.dto;

import java.util.List;

public class AdminCreatProjectDto {
    /**
     * 项目提交人id
     */
    private Integer projectCreaterId;

    /**
     * 项目提交机构列表
     */
    private List<Integer> investmentInstitutionIds;

    public Integer getProjectCreaterId() {
        return projectCreaterId;
    }

    public void setProjectCreaterId(Integer projectCreaterId) {
        this.projectCreaterId = projectCreaterId;
    }

    public List<Integer> getInvestmentInstitutionIds() {
        return investmentInstitutionIds;
    }

    public void setInvestmentInstitutionIds(List<Integer> investmentInstitutionIds) {
        this.investmentInstitutionIds = investmentInstitutionIds;
    }
}
