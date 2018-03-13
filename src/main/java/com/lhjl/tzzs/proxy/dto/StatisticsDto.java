package com.lhjl.tzzs.proxy.dto;

import com.lhjl.tzzs.proxy.model.OpStatisticsName;

public class StatisticsDto<T> extends CommonDto<T> {
    private OpStatisticsName statisticsName;

    public OpStatisticsName getStatisticsName() {
        return statisticsName;
    }

    public void setStatisticsName(OpStatisticsName statisticsName) {
        this.statisticsName = statisticsName;
    }
}
