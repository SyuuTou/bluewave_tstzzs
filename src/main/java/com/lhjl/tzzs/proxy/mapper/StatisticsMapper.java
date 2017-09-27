package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.HistogramList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatisticsMapper  {
    List<HistogramList> financingCountDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<HistogramList> financingAmountDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<HistogramList> financingSegmentationDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<HistogramList> financingCityDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<HistogramList> financingEducationExperienceDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<HistogramList> financingWorkExperienceDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<HistogramList> financingInvestmentDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
