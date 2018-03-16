package com.lhjl.tzzs.proxy.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.mapper.MetaFinancingMapper;
import com.lhjl.tzzs.proxy.mapper.OpStatisticsNameMapper;
import com.lhjl.tzzs.proxy.model.OpStatisticsName;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import com.lhjl.tzzs.proxy.utils.ComparatorHistogramList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

@Service(value = "evaluateService")
public class EvaluateServiceImpl implements EvaluateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluateServiceImpl.class);

    @Autowired
    private MetaFinancingMapper financingMapper;

    @Autowired
    private OpStatisticsNameMapper opStatisticsNameMapper;

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
    @Transactional(readOnly = true)
    @Override
    public StatisticsDto<List<HistogramList>> valuation(String investment, String roundName, String industryName, String cityName, String educationName, String workName, Integer from, Integer size) {

        DistributedCommonDto<List<HistogramList>> result = new DistributedCommonDto<List<HistogramList>>();
//        roundName= "Pre-A轮";
//        industryName="游戏";
        try {
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
            List<HistogramList> dataListNew = null;
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
            dataListNew = dataList;




            ComparatorHistogramList comparatorHistogramList = new ComparatorHistogramList();
            Collections.sort(dataList, comparatorHistogramList);
            if (dataList.size()>10){
                dataList = new ArrayList<>(dataList.subList(0,10));
            }


            NumberFormat numberFormat = NumberFormat.getInstance();
            Integer totalMoney = 0;
            Integer num = 0;
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            String evaluateAmount = "";

            String minAmount = "";
            String maxAmount = "";

            if (dataList != null&& dataList.size()>0) {
                if (dataList.get(0).getMoney() == 0 ){
                    HistogramList temp = dataList.get(0);
                    dataList.get(1).setDcount(temp.getDcount() + dataList.get(1).getDcount());
                    dataList = dataList.subList(1,dataList.size()-1);
                }
                for (HistogramList histogramList : dataList) {
                    num += histogramList.getDcount();
                    evaluateAmount+=histogramList.getMoney()+",";
                }
                evaluateAmount=evaluateAmount.substring(0,evaluateAmount.lastIndexOf(","));
                for (HistogramList histogramList : dataList) {
                    histogramList.setX(String.valueOf(histogramList.getMoney()));
                    histogramList.setY(numberFormat.format((float) histogramList.getDcount() / Float.valueOf(total) * 100));

                    totalMoney += histogramList.getMoney() * histogramList.getDcount();
                }


            }


            minAmount = dataList.get(0).getX();
            maxAmount = String.valueOf(dataList.get(dataList.size()-1).getMoney());

//        collect = financingMapper.queryValuationCount(investment,roundName,industryName,cityName,educationName,workName,avgBeginTime,avgEndTime);
//        total = Integer.valueOf(collect.get("total").toString());
            BigDecimal avg = financingMapper.queryValuationAvg(investment,roundName,industryName,cityName,educationName,workName,beginTime,endTime,granularity,minAmount,maxAmount);
            LOGGER.info("valuation:investment:{},roundName:{},evaluateAmount:{},avg:{},beginTime:{},endTime:{}",investment,roundName,evaluateAmount,avg,beginTime,endTime);

            result.setAvgMoney(avg.intValue());
            result.setMessage("success");
            result.setStatus(200);
            result.setData(dataList);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Cacheable(value = "financingAmount",keyGenerator = "wiselyKeyGenerator")
    @Transactional(readOnly = true)
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
        List<HistogramList> dataListNew = null;

        try {
            Integer index = from * size;

            Integer granularity = null;

            if (roundName.equals("天使轮")){
                granularity = 100;
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
            collect = financingMapper.queryFinancingCount(investment,roundName,industryName,cityName,educationName,workName,granularity,flag, avgBeginTime,avgEndTime);


            Integer total = Integer.valueOf(collect.get("total").toString());
            if (total < 10){
                result.setStatus(200);
                result.setMessage("no message");
                result.setData(new ArrayList<HistogramList>(0));
                return result;

            }else{
                dataList = financingMapper.queryFinancingAmount(investment,roundName,industryName,cityName,educationName,workName,granularity,flag,avgBeginTime,avgEndTime,index,size,20, total );
            }


            ComparatorHistogramList comparatorHistogramList = new ComparatorHistogramList();
            Collections.sort(dataList, comparatorHistogramList);
            dataListNew = dataList;

            if (dataList.size()>10){
                dataList = new ArrayList<>(dataList.subList(0,10));
            }

            Double sum = 0d;
            Integer endMoney = null;
            for(int i =0; i< dataListNew.size(); i++){
                sum  += Double.valueOf(dataListNew.get(i).getDcount())/Double.valueOf(total) ;
                if (sum>=0.8){
                    endMoney = dataListNew.get(i).getMoney();
                    break;
                }
            }
            NumberFormat numberFormat = NumberFormat.getInstance();

            Integer totalMoney = 0;

            Integer num = 0;
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);

            String evaluateAmount = "";

            String minAmount = "";
            String maxAmount = "";

            if (dataList != null&&dataList.size()>0) {
                if (dataList.get(0).getMoney() == 0 ){
                    HistogramList temp = dataList.get(0);
                    dataList.get(1).setDcount(temp.getDcount() + dataList.get(1).getDcount());
                    dataList = dataList.subList(1,dataList.size()-1);
                }
                for (HistogramList histogramList : dataList) {
                    num += histogramList.getDcount();
                    evaluateAmount+=histogramList.getMoney()+",";
                }
                evaluateAmount = evaluateAmount.substring(0,evaluateAmount.lastIndexOf(","));
                for (HistogramList histogramList : dataList) {

                    histogramList.setX(String.valueOf(histogramList.getMoney()));
                    histogramList.setY(numberFormat.format((float) histogramList.getDcount() / Float.valueOf(total) * 100));

                    totalMoney += histogramList.getMoney() * histogramList.getDcount();
                }


            }

            minAmount = dataList.get(0).getX();
            maxAmount = String.valueOf(dataList.get(dataList.size()-1).getMoney());

//        collect = financingMapper.queryFinancingCount(investment,roundName,industryName,cityName,educationName,workName,granularity,flag, avgBeginTime,avgEndTime);
//        total = Integer.valueOf(collect.get("total").toString());
            if (null != endMoney){
                maxAmount = String.valueOf(endMoney);
            }

            BigDecimal avg = financingMapper.queryFinancingAvgAmount(investment,roundName,industryName,cityName,educationName,workName,granularity,flag,avgBeginTime,avgEndTime,minAmount,maxAmount );
            LOGGER.info("valuation:investment:{},roundName:{},evaluateAmount:{},avg:{},flag{},beginTime:{},endTime:{}",investment,roundName,evaluateAmount,avg,flag,beginTime,endTime);
            result.setAvgMoney(avg.intValue());
            result.setMessage("success");
            OpStatisticsName query = new OpStatisticsName();
            query.setKey("amount_distribution");
            result.setStatisticsName(opStatisticsNameMapper.selectOne(query));
            result.setStatus(200);
            result.setData(dataList);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
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

        BigDecimal avgAmount = financingMapper.queryFinancingAvgAmount( investment,  roundName,  industryName,  cityName,  educationName,  workName, granularity, flag,beginTime,endTime, 20, total );
        result.setAvgMoney(avgAmount.intValue());
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }
}
