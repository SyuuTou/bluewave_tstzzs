package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/24.
 */
public class ProjectManageDto {

    private String token;

    private Integer companyId;

    private String interiorOrganization;

    private String investmentDecisionProcess;

    private BigDecimal totalAmount;

    private BigDecimal rmbAmount;

    private BigDecimal dollarAmount;

    private String bpEmail;
    /**
     * 投资类型
     */
    private List<String> investTypes;
    /**
     * 经典案例
     */
    private List<String> classicCases;
    /**
     * 主体类型
     */
    private Integer subjectType;
    

    public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }


    public String getInteriorOrganization() {
        return interiorOrganization;
    }

    public void setInteriorOrganization(String interiorOrganization) {
        this.interiorOrganization = interiorOrganization;
    }

    public String getInvestmentDecisionProcess() {
        return investmentDecisionProcess;
    }

    public void setInvestmentDecisionProcess(String investmentDecisionProcess) {
        this.investmentDecisionProcess = investmentDecisionProcess;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getRmbAmount() {
        return rmbAmount;
    }

    public void setRmbAmount(BigDecimal rmbAmount) {
        this.rmbAmount = rmbAmount;
    }

    public BigDecimal getDollarAmount() {
        return dollarAmount;
    }

    public void setDollarAmount(BigDecimal dollarAmount) {
        this.dollarAmount = dollarAmount;
    }


	public String getBpEmail() {
		return bpEmail;
	}

	public void setBpEmail(String bpEmail) {
		this.bpEmail = bpEmail;
	}

	public List<String> getInvestTypes() {
		return investTypes;
	}

	public void setInvestTypes(List<String> investTypes) {
		this.investTypes = investTypes;
	}

	public List<String> getClassicCases() {
		return classicCases;
	}

	public void setClassicCases(List<String> classicCases) {
		this.classicCases = classicCases;
	}



}
