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

    @Value("${statistics.avg.beginTime}")
    private String avgBeginTime ;

    @Value("${statistics.avg.endTime}")
    private String avgEndTime;

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
    public CommonDto<List<HistogramList>> valuation(String investment, String roundName, String industryName, String cityName, String educationName, String workName, Integer from, Integer size) {

        DistributedCommonDto<List<HistogramList>> result = new DistributedCommonDto<List<HistogramList>>();
//        roundName= "Pre-A轮";
//        industryName="游戏";
        if (StringUtils.isEmpty(roundName)){
            result.setStatus(511);
            result.setMessage("融资阶段必须选择。");
            return result;
        }

        Integer granularity = null;

        if (roundName.equals("天使轮")){
            granularity = 500;
        }else{
            granularity = 500;
        }

        List<HistogramList> dataList = null;
        Integer index = from * size;
        Map<String, Object> collect = financingMapper.queryValuationCount(investment,roundName,industryName,cityName,educationName,workName,beginTime,endTime);
        Integer total = Integer.valueOf(collect.get("total").toString());
        if (total < 5){
            result.setStatus(200);
            result.setMessage("no message");
            result.setData(new ArrayList<HistogramList>(0));
            return result;
        }else{
            dataList = financingMapper.queryValuation(investment,roundName,industryName,cityName,educationName,workName,granularity,beginTime,endTime,index,size);
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
            if (dataList.get(0).getMoney() == 0 ){
                HistogramList temp = dataList.get(0);
                dataList.get(1).setDcount(temp.getDcount() + dataList.get(1).getDcount());
                dataList = dataList.subList(1,dataList.size()-1);
            }
            for (HistogramList histogramList : dataList) {
                num += histogramList.getDcount();
            }
            for (HistogramList histogramList : dataList) {
                histogramList.setX(String.valueOf(histogramList.getMoney()));
                histogramList.setY(numberFormat.format((float) histogramList.getDcount() / Float.valueOf(num) * 100));

                totalMoney += histogramList.getMoney() * histogramList.getDcount();
            }


        }

        collect = financingMapper.queryValuationCount(investment,roundName,industryName,cityName,educationName,workName,avgBeginTime,avgEndTime);
        total = Integer.valueOf(collect.get("total").toString());
        BigDecimal avg = financingMapper.queryValuationAvg(investment,roundName,industryName,cityName,educationName,workName,avgBeginTime,avgEndTime,20, total);
        result.setAvgMoney(avg.intValue());
        result.setMessage("success");
        result.setStatus(200);
        result.setData(dataList);

        return result;
    }

    @Cacheable(value = "financingAmount",keyGenerator = "wiselyKeyGenerator")
    @Override
    public DistributedCommonDto<List<HistogramList>> financingAmount(String investment, String roundName, String industryName, String cityName, String educationName, String workName, Integer from, Integer size) {
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

        String flag = "total_amount";
//        if (investment.equals("1")){
//            flag = "amount";
//        }else{
//            flag = "total_amount";
//        }

        Map<String, Object> collect = null;
        collect = financingMapper.queryFinancingCount(investment,roundName,industryName,cityName,educationName,workName,granularity,flag, beginTime, endTime);



        Integer total = Integer.valueOf(collect.get("total").toString());
        if (total < 10){
            result.setStatus(200);
            result.setMessage("no message");
            result.setData(new ArrayList<HistogramList>(0));
            return result;

        }else{
            dataList = financingMapper.queryFinancingAmount(investment,roundName,industryName,cityName,educationName,workName,granularity,flag,beginTime,endTime,index,size,20, total );
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
            if (dataList.get(0).getMoney() == 0 ){
                HistogramList temp = dataList.get(0);
                dataList.get(1).setDcount(temp.getDcount() + dataList.get(1).getDcount());
                dataList = dataList.subList(1,dataList.size()-1);
            }
            for (HistogramList histogramList : dataList) {
                num += histogramList.getDcount();
            }
            for (HistogramList histogramList : dataList) {

                histogramList.setX(String.valueOf(histogramList.getMoney()));
                histogramList.setY(numberFormat.format((float) histogramList.getDcount() / Float.valueOf(num) * 100));

                totalMoney += histogramList.getMoney() * histogramList.getDcount();
            }


        }
        collect = financingMapper.queryFinancingCount(investment,roundName,industryName,cityName,educationName,workName,granularity,flag, avgBeginTime,avgEndTime);



        total = Integer.valueOf(collect.get("total").toString());
        BigDecimal avg = financingMapper.queryFinancingAvgAmount(investment,roundName,industryName,cityName,educationName,workName,granularity,flag,avgBeginTime,avgEndTime,20, total );
        result.setAvgMoney(avg.intValue());
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

    @Override
    public DistributedCommonDto<BigDecimal> financingAmountAvg(String investment, String roundName, String industryName, String cityName, String educationName, String workName, Integer from, Integer size) {

        DistributedCommonDto<BigDecimal> result = new DistributedCommonDto<>();
        Integer granularity = null;

        if (roundName.equals("天使轮")){
            granularity = 50;
        }else{
            granularity = 100;
        }

        String flag = "total_amount";
        if (investment.equals("1")){
            flag = "amount";
        }else{
            flag = "total_amount";
        }
        Map<String, Object> collect = null;
        collect = financingMapper.queryFinancingCount(investment,roundName,industryName,cityName,educationName,workName,granularity,flag, avgBeginTime, avgEndTime);



        Integer total = Integer.valueOf(collect.get("total").toString());

        BigDecimal avgAmount = financingMapper.queryFinancingAvgAmount( investment,  roundName,  industryName,  cityName,  educationName,  workName, granularity, flag,avgBeginTime,avgEndTime, 20, total );
        result.setAvgMoney(avgAmount.intValue());
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }
}
