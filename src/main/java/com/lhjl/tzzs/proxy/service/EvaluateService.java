package com.lhjl.tzzs.proxy.service;


import com.lhjl.tzzs.proxy.dto.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface EvaluateService {
    StatisticsDto<Map<String, List<LabelList>>> queryHotData();

    StatisticsDto<List<HistogramList>> valuation(String investment, String roundName, String industryName, String cityName, String educationName, String workName, Integer from, Integer size);

    StatisticsDto<List<HistogramList>> financingAmount(String investment, String roundName, String industryName, String cityName, String educationName, String workName, Integer from, Integer size);

    StatisticsDto<List<LabelList>> queryHotIndustry();

    DistributedCommonDto<BigDecimal> financingAmountAvg(String investment, String roundName, String industryName, String cityName, String educationName, String workName, Integer from, Integer size);
}
