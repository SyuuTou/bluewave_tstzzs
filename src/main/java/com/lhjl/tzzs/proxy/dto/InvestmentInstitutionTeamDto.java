package com.lhjl.tzzs.proxy.dto;

import java.util.List;

public class InvestmentInstitutionTeamDto {

    /**机构团队类型*/
    private String temaType;

    /**成员列表*/
    private List<InvestmentInstitutionTeamMemberDto> teamMember;

    public String getTemaType() {
        return temaType;
    }

    public void setTemaType(String temaType) {
        this.temaType = temaType;
    }

    public List<InvestmentInstitutionTeamMemberDto> getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(List<InvestmentInstitutionTeamMemberDto> teamMember) {
        this.teamMember = teamMember;
    }
}
