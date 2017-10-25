package com.lhjl.tzzs.proxy.dto;

/**
 * 投资偏好返回行业数据
 * Created by 蓝海巨浪 on 2017/10/24.
 */
public class InvestorsDemandLabel {

    private int industrycl7describe;
    private int industrycl7orderofarr;
    private String industrycl7tradename;
    private String industryta7tradename;
    private boolean isgouxuan;

    public int getIndustrycl7describe() {
        return industrycl7describe;
    }

    public void setIndustrycl7describe(int industrycl7describe) {
        this.industrycl7describe = industrycl7describe;
    }

    public int getIndustrycl7orderofarr() {
        return industrycl7orderofarr;
    }

    public void setIndustrycl7orderofarr(int industrycl7orderofarr) {
        this.industrycl7orderofarr = industrycl7orderofarr;
    }

    public String getIndustrycl7tradename() {
        return industrycl7tradename;
    }

    public void setIndustrycl7tradename(String industrycl7tradename) {
        this.industrycl7tradename = industrycl7tradename;
    }

    public String getIndustryta7tradename() {
        return industryta7tradename;
    }

    public void setIndustryta7tradename(String industryta7tradename) {
        this.industryta7tradename = industryta7tradename;
    }

    public boolean isIsgouxuan() {
        return isgouxuan;
    }

    public void setIsgouxuan(boolean isgouxuan) {
        this.isgouxuan = isgouxuan;
    }
}
