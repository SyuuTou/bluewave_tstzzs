package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.StatisticsDto;

import java.util.List;

public interface StatisticsService {
    StatisticsDto<List<HistogramList>> financingCountDistributed(String institutionType, String from, String size);

    StatisticsDto<List<HistogramList>> financingAmountDistributed(String institutionType, String from, String size);

    StatisticsDto<List<HistogramList>> financingSegmentationDistributed(String institutionType, String from, String size);

    StatisticsDto<List<HistogramList>> financingCityDistributed(String institutionType, String from, String size);

    StatisticsDto<List<HistogramList>> financingEducationExperienceDistributed(String institutionType, String from, String size);

    StatisticsDto<List<HistogramList>> financingWorkExperienceDistributed(String institutionType, String from, String size);

    StatisticsDto<List<HistogramList>> financingInvestmentDistributed(String institutionType, String from, String size);

    StatisticsDto<List<HistogramList>> financingInvestmentCharacteristicDistributed(String institutionType, String from, String size);

    StatisticsDto<List<HistogramList>> financingInvestmentSegmentationDistributed(String institutionType, String from, String size);
}
