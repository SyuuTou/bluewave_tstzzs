package com.lhjl.tzzs.proxy.service;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.LabelList;

import java.util.List;
import java.util.Map;

public interface EvaluateService {
    CommonDto<Map<String, List<LabelList>>> queryHotData();

    CommonDto<List<HistogramList>> valuation(String investment,String roundName, String industryName, String cityName, String educationName, String workName, Integer from, Integer size);

    CommonDto<List<HistogramList>> financingAmount(String investment,String roundName, String industryName, String cityName, String educationName, String workName, Integer from, Integer size);

    CommonDto<List<LabelList>> queryHotIndustry();

}
