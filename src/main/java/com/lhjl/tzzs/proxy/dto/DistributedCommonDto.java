package com.lhjl.tzzs.proxy.dto;

public class DistributedCommonDto<T> extends CommonDto<T> {
    private Integer avgMoney = 0;

    public Integer getAvgMoney() {
        return avgMoney;
    }

    public void setAvgMoney(Integer avgMoney) {
        this.avgMoney = avgMoney;
    }
}
