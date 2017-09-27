package com.lhjl.tzzs.proxy.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.DistributedCommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.mapper.MetaFinancingMapper;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Value("${statistics.beginTime}")
    private String beginTime ;

    @Value("${statistics.endTime}")
    private String endTime;

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

        DistributedCommonDto<List<HistogramList>> result = new DistributedCommonDto<List<HistogramList>>();
//        roundName= "Pre-A轮";
//        industryName="游戏";
        if (StringUtils.isEmpty(roundName)){
            result.setStatus(511);
            result.setMessage("融资阶段必须选择。");
            return result;
        }
        List<HistogramList> dataList = null;

        Map<String, Object> collect = financingMapper.queryValuationCount(roundName,industryName,cityName,educationName,workName);
        Integer total = Integer.valueOf(collect.get("total").toString());
        if (total < 5){
            collect = financingMapper.queryValuationCount(roundName,industryName,cityName,null,workName);
        }else{
            dataList = financingMapper.queryValuation(roundName,industryName,cityName,educationName,workName);
        }
        total = Integer.valueOf(collect.get("total").toString());
        if (total < 5){
            collect = financingMapper.queryValuationCount(roundName,industryName,cityName,null,null);
        }else{
            dataList = financingMapper.queryValuation(roundName,industryName,cityName,null,workName);
        }
        total = Integer.valueOf(collect.get("total").toString());
        if (total < 5){
            collect = financingMapper.queryValuationCount(roundName,industryName,null,null,null);
        }else{
            dataList = financingMapper.queryValuation(roundName,industryName,cityName,null,null);
        }
        total = Integer.valueOf(collect.get("total").toString());
        if (total < 5){
            collect = financingMapper.queryValuationCount(roundName,null,null,null,null);
        }else{
            dataList = financingMapper.queryValuation(roundName,industryName,null,null,null);
        }

        total = Integer.valueOf(collect.get("total").toString());
        if (total < 5) {
            dataList = financingMapper.queryValuation(roundName, null, null, null, null);
        }


        NumberFormat numberFormat = NumberFormat.getInstance();
        Integer totalMoney = 0;
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        for (HistogramList histogramList: dataList) {
            histogramList.setX(String.valueOf(histogramList.getMoney()));
            histogramList.setY(numberFormat.format((float) histogramList.getDcount() / Float.valueOf(collect.get("total").toString()) * 100));

            totalMoney += histogramList.getMoney()*histogramList.getDcount();
        }

        if (totalMoney > 0) {
            result.setAvgMoney(totalMoney/Integer.valueOf(collect.get("total").toString()));
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(dataList);

        return result;
    }

    @Override
    public CommonDto<List<HistogramList>> financingAmount(String roundName, String industryName, String cityName, String educationName, String workName) {
        DistributedCommonDto<List<HistogramList>> result = new DistributedCommonDto<List<HistogramList>>();
//        roundName= "Pre-A轮";
//        industryName="游戏";
        if (StringUtils.isEmpty(roundName)){
            result.setStatus(511);
            result.setMessage("融资阶段必须选择。");
            return result;
        }


        List<HistogramList> dataList = null;

        Map<String, Object> collect = financingMapper.queryFinancingCount(roundName,industryName,cityName,educationName,workName);
        Integer total = Integer.valueOf(collect.get("total").toString());
        if (total < 5){
            collect = financingMapper.queryFinancingCount(roundName,industryName,cityName,null,workName);
        }else{
            dataList = financingMapper.queryFinancingAmount(roundName,industryName,cityName,educationName,workName);
        }
        total = Integer.valueOf(collect.get("total").toString());
        if (total < 5){
            collect = financingMapper.queryFinancingCount(roundName,industryName,cityName,null,null);
        }else{
            dataList = financingMapper.queryFinancingAmount(roundName,industryName,cityName,null,workName);
        }
        total = Integer.valueOf(collect.get("total").toString());
        if (total < 5){
            collect = financingMapper.queryFinancingCount(roundName,industryName,null,null,null);
        }else{
            dataList = financingMapper.queryFinancingAmount(roundName,industryName,cityName,null,null);
        }
        total = Integer.valueOf(collect.get("total").toString());
        if (total < 5){
            collect = financingMapper.queryFinancingCount(roundName,null,null,null,null);
        }else{
            dataList = financingMapper.queryFinancingAmount(roundName,industryName,null,null,null);
        }

        total = Integer.valueOf(collect.get("total").toString());
        if (total < 5) {
            dataList = financingMapper.queryFinancingAmount(roundName, null, null, null, null);
        }
        NumberFormat numberFormat = NumberFormat.getInstance();

        Integer totalMoney = 0;
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        for (HistogramList histogramList: dataList) {
            histogramList.setX(String.valueOf(histogramList.getMoney()));
            histogramList.setY(numberFormat.format((float) histogramList.getDcount() / Float.valueOf(collect.get("total").toString()) * 100));

            totalMoney += histogramList.getMoney()*histogramList.getDcount();
        }

        if (totalMoney > 0) {
            result.setAvgMoney(totalMoney/Integer.valueOf(collect.get("total").toString()));
        }
        result.setMessage("success");
        result.setStatus(200);
        result.setData(dataList);
        return result;
    }
}
