package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.HistogramList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatisticsMapper  {
    List<HistogramList> financingCountDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("froma") Integer froma,@Param("sizea") Integer sizea);

    List<HistogramList> financingAmountDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("froma") Integer froma,@Param("sizea") Integer sizea);

    List<HistogramList> financingSegmentationDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("froma") Integer froma,@Param("sizea") Integer sizea);

    List<HistogramList> financingCityDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("froma") Integer froma,@Param("sizea") Integer sizea);

    List<HistogramList> financingEducationExperienceDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("froma") Integer froma,@Param("sizea") Integer sizea);

    List<HistogramList> financingWorkExperienceDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("froma") Integer froma,@Param("sizea") Integer sizea);

    List<HistogramList> financingInvestmentDistributed(@Param("jgtype") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("froma") Integer froma,@Param("sizea") Integer sizea);
}
