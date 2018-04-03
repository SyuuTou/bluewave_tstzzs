package com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditdetaildto;

/**
 * Created by lanhaijulang on 2018/4/2.
 */
public class InvestorSmartSearchOutputDto {

    /**
     * 投资人id
     */
    private Integer investorId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 投资机构简称
     */
    private String shortName;

    /**
     * 职务
     */
    private String position;

    public Integer getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
