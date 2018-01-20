package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;

/**
 * 投资偏好请求参数
 * Created by 蓝海巨浪 on 2017/10/24.
 */
public class InvestorsDemandDto {
    /**
     * 用户token
     */
    private String token;
    /**
     * 行业名称
     */
    private String industryta7tradename;
    /**
     * 投资阶段
     */
    private String touzi;
    /**
     * 城市/地域
     */
    private String city;
    /**
     * 金额上限
     */
    private String shangxian;
    /**
     * 金额下限
     */
    private String xiaxian;

    /**
     * 美元开始金额
     */
    private BigDecimal startdoller;

    /**
     * 美元结束金额
     */
    private BigDecimal enddoller;
    /**
     * 需求描述
     */
    private String xuqiu;
    /**
     * 最近关注细分赛道
     */
    private String user7recentlyco_noana;
    /**
     * 关注的创始人特质
     */
    private String user7foundertra_noana;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIndustryta7tradename() {
        return industryta7tradename;
    }

    public void setIndustryta7tradename(String industryta7tradename) {
        this.industryta7tradename = industryta7tradename;
    }

    public String getTouzi() {
        return touzi;
    }

    public void setTouzi(String touzi) {
        this.touzi = touzi;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShangxian() {
        return shangxian;
    }

    public void setShangxian(String shangxian) {
        this.shangxian = shangxian;
    }

    public String getXiaxian() {
        return xiaxian;
    }

    public void setXiaxian(String xiaxian) {
        this.xiaxian = xiaxian;
    }

    public String getXuqiu() {
        return xuqiu;
    }

    public void setXuqiu(String xuqiu) {
        this.xuqiu = xuqiu;
    }

    public String getUser7recentlyco_noana() {
        return user7recentlyco_noana;
    }

    public void setUser7recentlyco_noana(String user7recentlyco_noana) {
        this.user7recentlyco_noana = user7recentlyco_noana;
    }

    public String getUser7foundertra_noana() {
        return user7foundertra_noana;
    }

    public void setUser7foundertra_noana(String user7foundertra_noana) {
        this.user7foundertra_noana = user7foundertra_noana;
    }

    public BigDecimal getStartdoller() {
        return startdoller;
    }

    public void setStartdoller(BigDecimal startdoller) {
        this.startdoller = startdoller;
    }

    public BigDecimal getEnddoller() {
        return enddoller;
    }

    public void setEnddoller(BigDecimal enddoller) {
        this.enddoller = enddoller;
    }
}
