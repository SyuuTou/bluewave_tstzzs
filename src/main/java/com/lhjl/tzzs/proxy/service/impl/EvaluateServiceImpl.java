package com.lhjl.tzzs.proxy.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.mapper.MetaFinancingMapper;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "evaluateService")
public class EvaluateServiceImpl implements EvaluateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluateServiceImpl.class);

    @Autowired
    private MetaFinancingMapper financingMapper;

    @Override
    public CommonDto<Map<String, List<LabelList>>> queryHotData() {

        CommonDto<Map<String, List<LabelList>>> result = new CommonDto<Map<String, List<LabelList>>>();
        Map<String, List<LabelList>> dataMap = new HashMap<String, List<LabelList>>();
        List<LabelList> labelLists = new ArrayList<LabelList>();
        // 热点城市

        labelLists = financingMapper.queryHotCity();
        dataMap.put("cityKey",labelLists);

        // 热点教育背景
        labelLists = financingMapper.queryHotEducation();
        dataMap.put("educationKey",labelLists);
        // 热点工作经历
        labelLists = financingMapper.queryHotWork();
        dataMap.put("workKey",labelLists);

        result.setData(dataMap);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }

    @Override
    public CommonDto<List<HistogramList>> valuation(String roundName, String industryName, String cityName, String educationName, String workName) {

        CommonDto<List<HistogramList>> result = new CommonDto<List<HistogramList>>();
//        if (StringUtils.isEmpty(roundName)){
//            result.setStatus(511);
//            result.setMessage("融资阶段必须选择。");
//            return result;
//        }

        roundName = "A轮";

        List<HistogramList> dataList = financingMapper.queryValuation(roundName,industryName,cityName,educationName,workName);

        Integer total = financingMapper.queryCount(roundName,industryName,cityName,educationName,workName);

        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        for (HistogramList histogramList: dataList) {
            histogramList.setX(String.valueOf(histogramList.getMoney()));
            histogramList.setY(numberFormat.format((float) histogramList.getDcount() / (float) total * 100));
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(dataList);

        return result;
    }

    @Override
    public CommonDto<List<HistogramList>> financingAmount(String roundName, String industryName, String cityName, String educationName, String workName) {
        CommonDto<List<HistogramList>> result = new CommonDto<List<HistogramList>>();
        if (StringUtils.isEmpty(roundName)){
            result.setStatus(511);
            result.setMessage("融资阶段必须选择。");
            return result;
        }

        List<HistogramList> dataList = financingMapper.queryFinancingAmount(roundName, industryName, cityName, educationName, workName);
        Integer total = financingMapper.queryCount(roundName,industryName,cityName,educationName,workName);

        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        for (HistogramList histogramList: dataList) {
            histogramList.setX(String.valueOf(histogramList.getMoney()));
            histogramList.setY(numberFormat.format((float) histogramList.getDcount() / (float) total * 100));
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(dataList);
        return result;
    }
}
