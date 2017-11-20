package com.lhjl.tzzs.proxy.dto;

public class InvestmentInstitutionComplexOutputDto {
    /**
     * 机构名称
     */
   private String investmentInstitutionName;
    /**
     * 机构logo
     */
   private String investmentInstitutionLogo;
    /**
     * 机构一句话介绍
     */
   private String investmentInstitutionDesc;

    public String getInvestmentInstitutionName() {
        return investmentInstitutionName;
    }

    public void setInvestmentInstitutionName(String investmentInstitutionName) {
        this.investmentInstitutionName = investmentInstitutionName;
    }

    public String getInvestmentInstitutionLogo() {
        return investmentInstitutionLogo;
    }

    public void setInvestmentInstitutionLogo(String investmentInstitutionLogo) {
        this.investmentInstitutionLogo = investmentInstitutionLogo;
    }

    public String getInvestmentInstitutionDesc() {
        return investmentInstitutionDesc;
    }

    public void setInvestmentInstitutionDesc(String investmentInstitutionDesc) {
        this.investmentInstitutionDesc = investmentInstitutionDesc;
    }
}
