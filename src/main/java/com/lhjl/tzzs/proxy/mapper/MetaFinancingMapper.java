package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.model.MetaFinancing;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MetaFinancingMapper  extends OwnerMapper<MetaFinancing> {

    List<LabelList> queryHotCity();

    List<LabelList> queryHotEducation();

    List<LabelList> queryHotWork();

    List<HistogramList> queryValuation(@Param("roundName") String roundName, @Param("industryName") String industryName, @Param("cityName") String cityName, @Param("educationName") String educationName, @Param("workName") String workName);

    List<HistogramList> queryFinancingAmount(@Param("roundName") String roundName, @Param("industryName") String industryName, @Param("cityName") String cityName, @Param("educationName") String educationName, @Param("workName") String workName);

    Map<String,Object> queryFinancingCount(@Param("roundName") String roundName, @Param("industryName") String industryName, @Param("cityName") String cityName, @Param("educationName") String educationName, @Param("workName") String workName);

    Map<String,Object> queryValuationCount(@Param("roundName") String roundName, @Param("industryName") String industryName, @Param("cityName") String cityName, @Param("educationName") String educationName, @Param("workName") String workName);
}