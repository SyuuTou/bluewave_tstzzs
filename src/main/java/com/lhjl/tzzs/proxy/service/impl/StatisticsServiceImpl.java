package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.mapper.StatisticsMapper;
import com.lhjl.tzzs.proxy.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsService.class);

    @Value("${statistics.beginTime}")
    private String beginTime ;

    @Value("${statistics.endTime}")
    private String endTime;

    @Autowired
    private StatisticsMapper statisticsMapper;
    @Override
    public CommonDto<List<HistogramList>> financingCountDistributed(String institutionType,String from ,String size) {

        int from1  = Integer.parseInt(from);
        int size1 = Integer.parseInt(size);

        int froma = from1*size1;
        int sizea = size1;

        CommonDto<List<HistogramList>> result = new CommonDto<List<HistogramList>>();
        Integer type = null;
        if (institutionType.equals("50")){
            type = 1;
        }
        List<HistogramList>  histogramLists = statisticsMapper.financingCountDistributed(type,beginTime,endTime,froma,sizea);
        result.setData(histogramLists);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

    @Override
    public CommonDto<List<HistogramList>> financingAmountDistributed(String institutionType,String from, String size) {

        int from1  = Integer.parseInt(from);
        int size1 = Integer.parseInt(size);

        int froma = from1*size1;
        int sizea = size1;

        CommonDto<List<HistogramList>> result = new CommonDto<List<HistogramList>>();
        Integer type = null;
        if (institutionType.equals("50")){
            type = 1;
        }
        List<HistogramList>  histogramLists = statisticsMapper.financingAmountDistributed(type,beginTime,endTime,froma,sizea);
        result.setData(histogramLists);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

    @Override
    public CommonDto<List<HistogramList>> financingSegmentationDistributed(String institutionType,String from, String size) {

        int from1  = Integer.parseInt(from);
        int size1 = Integer.parseInt(size);

        int froma = from1*size1;
        int sizea = size1;

        CommonDto<List<HistogramList>> result = new CommonDto<List<HistogramList>>();
        Integer type = null;
        if (institutionType.equals("50")){
            type = 1;
        }
        List<HistogramList>  histogramLists = statisticsMapper.financingSegmentationDistributed(type,beginTime,endTime,froma,sizea);
        result.setData(histogramLists);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

    @Override
    public CommonDto<List<HistogramList>> financingCityDistributed(String institutionType,String from, String size) {

        int from1  = Integer.parseInt(from);
        int size1 = Integer.parseInt(size);

        int froma = from1*size1;
        int sizea = size1;

        CommonDto<List<HistogramList>> result = new CommonDto<List<HistogramList>>();
        Integer type = null;
        if (institutionType.equals("50")){
            type = 1;
        }
        List<HistogramList>  histogramLists = statisticsMapper.financingCityDistributed(type,beginTime,endTime,froma,sizea);
        result.setData(histogramLists);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

    @Override
    public CommonDto<List<HistogramList>> financingEducationExperienceDistributed(String from, String size) {

        int from1  = Integer.parseInt(from);
        int size1 = Integer.parseInt(size);

        int froma = from1*size1;
        int sizea = size1;

        CommonDto<List<HistogramList>> result = new CommonDto<List<HistogramList>>();
        Integer type = null;
        List<HistogramList>  histogramLists = statisticsMapper.financingEducationExperienceDistributed(type,beginTime,endTime,froma,sizea);
        result.setData(histogramLists);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

    @Override
    public CommonDto<List<HistogramList>> financingWorkExperienceDistributed(String from, String size) {

        int from1  = Integer.parseInt(from);
        int size1 = Integer.parseInt(size);

        int froma = from1*size1;
        int sizea = size1;

        CommonDto<List<HistogramList>> result = new CommonDto<List<HistogramList>>();
        Integer type = null;
        List<HistogramList>  histogramLists = statisticsMapper.financingWorkExperienceDistributed(type,beginTime,endTime,froma,sizea);
        result.setData(histogramLists);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

    @Override
    public CommonDto<List<HistogramList>> financingInvestmentDistributed(String from, String size) {

        int from1  = Integer.parseInt(from);
        int size1 = Integer.parseInt(size);

        int froma = from1*size1;
        int sizea = size1;

        CommonDto<List<HistogramList>> result = new CommonDto<List<HistogramList>>();
        Integer type = null;
        List<HistogramList>  histogramLists = statisticsMapper.financingInvestmentDistributed(type,beginTime,endTime,froma,sizea);
        result.setData(histogramLists);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }
}
