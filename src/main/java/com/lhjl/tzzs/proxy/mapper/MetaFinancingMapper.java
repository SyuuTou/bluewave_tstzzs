package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.LabelList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MetaFinancingMapper  {

    List<LabelList> queryHotCity();

    List<LabelList> queryHotEducation();

    List<LabelList> queryHotWork();

    List<HistogramList> queryValuation(@Param("roundName") String roundName, @Param("industryName") String industryName, @Param("cityName") String cityName, @Param("educationName") String educationName, @Param("workName") String workName);

    List<HistogramList> queryFinancingAmount(@Param("roundName") String roundName, @Param("industryName") String industryName, @Param("cityName") String cityName, @Param("educationName") String educationName, @Param("workName") String workName);

    Integer queryCount(@Param("roundName") String roundName, @Param("industryName") String industryName, @Param("cityName") String cityName, @Param("educationName") String educationName, @Param("workName") String workName);
}