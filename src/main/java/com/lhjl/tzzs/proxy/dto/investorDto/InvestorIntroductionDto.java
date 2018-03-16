package com.lhjl.tzzs.proxy.dto.investorDto;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public class InvestorIntroductionDto {

    private Integer investorId;

    private String investorIntroduction;

    public Integer getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

    public String getInvestorIntroduction() {
        return investorIntroduction;
    }

    public void setInvestorIntroduction(String investorIntroduction) {
        this.investorIntroduction = investorIntroduction;
    }

	@Override
	public String toString() {
		return "InvestorIntroductionDto [investorId=" + investorId + ", investorIntroduction=" + investorIntroduction
				+ "]";
	}
    
}
