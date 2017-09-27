package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;

import java.util.List;

public interface StatisticsService {
    CommonDto<List<HistogramList>> financingCountDistributed(String institutionType);

    CommonDto<List<HistogramList>> financingAmountDistributed(String institutionType);

    CommonDto<List<HistogramList>> financingSegmentationDistributed(String institutionType);

    CommonDto<List<HistogramList>> financingCityDistributed(String institutionType);

    CommonDto<List<HistogramList>> financingEducationExperienceDistributed();

    CommonDto<List<HistogramList>> financingWorkExperienceDistributed();

    CommonDto<List<HistogramList>> financingInvestmentDistributed();
}
