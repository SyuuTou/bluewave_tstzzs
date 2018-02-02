package com.lhjl.tzzs.proxy.dto.investorDto;

/**
 * Created by lanhaijulang on 2018/1/31.
 */
public class InvestorCertificationDto {

    /**
     * 投资人id
     */
    private Integer investorId;

    /**
     * 投资人认证类型
     */
    private Integer investorType;

    /**
     * 投资案例
     */
    private String[] investCase;

    /**
     *工作名片
     */
    private String businessCard;

    /**
     *工作名片反面
     */
    private String businessCardOpposite;

    /**
     * 认证说明
     */
    private String certificationDesc;

    public String getBusinessCardOpposite() {
        return businessCardOpposite;
    }

    public void setBusinessCardOpposite(String businessCardOpposite) {
        this.businessCardOpposite = businessCardOpposite;
    }

    public Integer getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

    public Integer getInvestorType() {
        return investorType;
    }

    public void setInvestorType(Integer investorType) {
        this.investorType = investorType;
    }

    public String[] getInvestCase() {
        return investCase;
    }

    public void setInvestCase(String[] investCase) {
        this.investCase = investCase;
    }

    public String getBusinessCard() {
        return businessCard;
    }

    public void setBusinessCard(String businessCard) {
        this.businessCard = businessCard;
    }

    public String getCertificationDesc() {
        return certificationDesc;
    }

    public void setCertificationDesc(String certificationDesc) {
        this.certificationDesc = certificationDesc;
    }
}
