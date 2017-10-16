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
import org.springframework.cache.annotation.Cacheable;
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

    @Value("${statistics.beginTime}")
    private String beginTime ;

    @Value("${statistics.endTime}")
    private String endTime;

    @Cacheable(value = "queryHotData",keyGenerator = "wiselyKeyGenerator")
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

        labelLists = financingMapper.queryHotIndustry();
        dataMap.put("industryKey", labelLists);

        result.setData(dataMap);
        result.setMessage("success");
        result.setStatus(200);




        return result;
    }


    @Cacheable(value = "valuation",keyGenerator = "wiselyKeyGenerator")
    @Override
    public CommonDto<List<HistogramList>> valuation(String roundName, String industryName, String cityName, String educationName, String workName, Integer from, Integer size) {

        DistributedCommonDto<List<HistogramList>> result = new DistributedCommonDto<List<HistogramList>>();
//        roundName= "Pre-A轮";
//        industryName="游戏";
        if (StringUtils.isEmpty(roundName)){
            result.setStatus(511);
            result.setMessage("融资阶段必须选择。");
            return result;
        }
        List<HistogramList> dataList = null;
        Integer index = from * size;
        Map<String, Object> collect = financingMapper.queryValuationCount(roundName,industryName,cityName,educationName,workName,beginTime,endTime);
        Integer total = Integer.valueOf(collect.get("total").toString());
        if (total < 5){
            result.setStatus(200);
            result.setMessage("no message");
            result.setData(new ArrayList<HistogramList>(0));
            return result;
        }else{
            dataList = financingMapper.queryValuation(roundName,industryName,cityName,educationName,workName,beginTime,endTime,index,size);
        }

        if (dataList.size()>10){
            dataList = new ArrayList<HistogramList>(dataList.subList(0,10));
        }

        NumberFormat numberFormat = NumberFormat.getInstance();
        Integer totalMoney = 0;
        Integer num = 0;
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        if (dataList != null&& dataList.size()>0) {
            for (HistogramList histogramList : dataList) {
                num += histogramList.getDcount();
            }
            for (HistogramList histogramList : dataList) {
                histogramList.setX(String.valueOf(histogramList.getMoney()));
                histogramList.setY(numberFormat.format((float) histogramList.getDcount() / Float.valueOf(num) * 100));

                totalMoney += histogramList.getMoney() * histogramList.getDcount();
            }

            if (totalMoney > 0) {
                result.setAvgMoney(totalMoney / num);
            }
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(dataList);

        return result;
    }

    @Cacheable(value = "financingAmount",keyGenerator = "wiselyKeyGenerator")
    @Override
    public CommonDto<List<HistogramList>> financingAmount(String roundName, String industryName, String cityName, String educationName, String workName, Integer from, Integer size) {
        DistributedCommonDto<List<HistogramList>> result = new DistributedCommonDto<List<HistogramList>>();
//        roundName= "Pre-A轮";
//        industryName="游戏";
        if (StringUtils.isEmpty(roundName)){
            result.setStatus(511);
            result.setMessage("融资阶段必须选择。");
            return result;
        }


        List<HistogramList> dataList = null;

        Integer index = from * size;

        Integer granularity = null;

        if (roundName.equals("天使轮")){
            granularity = 50;
        }else{
            granularity = 100;
        }


        Map<String, Object> collect = null;
        collect = financingMapper.queryFinancingCount(roundName,industryName,cityName,educationName,workName,granularity, beginTime, endTime);

        Integer total = Integer.valueOf(collect.get("total").toString());
        if (total < 5){
            result.setStatus(200);
            result.setMessage("no message");
            result.setData(new ArrayList<HistogramList>(0));
            return result;

        }else{
            dataList = financingMapper.queryFinancingAmount(roundName,industryName,cityName,educationName,workName,granularity,beginTime,endTime,index,size);
        }

        if (dataList.size()>10){
            dataList = new ArrayList<HistogramList>(dataList.subList(0,10));
        }

        NumberFormat numberFormat = NumberFormat.getInstance();

        Integer totalMoney = 0;

        Integer num = 0;
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        if (dataList != null&&dataList.size()>0) {
            for (HistogramList histogramList : dataList) {
                num += histogramList.getDcount();
            }
            for (HistogramList histogramList : dataList) {
                histogramList.setX(String.valueOf(histogramList.getMoney()));
                histogramList.setY(numberFormat.format((float) histogramList.getDcount() / Float.valueOf(num) * 100));

                totalMoney += histogramList.getMoney() * histogramList.getDcount();
            }

            if (totalMoney > 0) {
                result.setAvgMoney(totalMoney / num);
            }
        }
        result.setMessage("success");
        result.setStatus(200);
        result.setData(dataList);
        return result;
    }

    @Cacheable(value = "queryHotIndustry",keyGenerator = "wiselyKeyGenerator")
    @Override
    public CommonDto<List<LabelList>> queryHotIndustry() {
        CommonDto<List<LabelList>> result = new CommonDto<List<LabelList>>();
        List<LabelList> labelLists = financingMapper.queryHotIndustry();

        result.setData(labelLists);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }
}
