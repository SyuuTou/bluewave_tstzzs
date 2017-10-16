package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;

import java.util.List;

public interface StatisticsService {
    CommonDto<List<HistogramList>> financingCountDistributed(String institutionType,String from, String size);

    CommonDto<List<HistogramList>> financingAmountDistributed(String institutionType,String from, String size);

    CommonDto<List<HistogramList>> financingSegmentationDistributed(String institutionType,String from, String size);

    CommonDto<List<HistogramList>> financingCityDistributed(String institutionType,String from, String size);

    CommonDto<List<HistogramList>> financingEducationExperienceDistributed(String institutionType, String from, String size);

    CommonDto<List<HistogramList>> financingWorkExperienceDistributed(String institutionType, String from, String size);

    CommonDto<List<HistogramList>> financingInvestmentDistributed(String institutionType, String from, String size);
}
