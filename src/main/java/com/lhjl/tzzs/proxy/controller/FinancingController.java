package com.lhjl.tzzs.proxy.controller;


import com.lhjl.tzzs.proxy.dto.StatisticsDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@RestController
public class FinancingController {

    private static final Logger log = LoggerFactory.getLogger(FinancingController.class);


    @Resource
    private EvaluateService evaluateService;

    /**
     * 评估使用的热点数据
     * @return
     */
    @GetMapping("hotData")
    public StatisticsDto<Map<String,List<LabelList>>> hotData(){
        StatisticsDto<Map<String,List<LabelList>>> result = null;
        try {
            result = evaluateService.queryHotData();
        }catch (Exception e){
            result = new StatisticsDto<Map<String, List<LabelList>>>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
}

    @GetMapping("hotIndustry")
    public StatisticsDto<List<LabelList>> hotIndustry(){
        StatisticsDto<List<LabelList>> result = null;
        try {
            result = evaluateService.queryHotIndustry();
        }catch (Exception e){
            result = new StatisticsDto<>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 融资金额
     * @param investment 投资机构（1：50机构，-1：非50机构）
     * @param roundName  轮次名称
     * @param industryName 行业领域名称
     * @param cityName  城市名称
     * @param educationName 教育背景
     * @param workName 工作背景
     * @return
     */
    @GetMapping("financing/amount/list")
    public StatisticsDto<List<HistogramList>> financingAmount(@RequestParam(required = false,defaultValue = "-1") String investment, @RequestParam(required = false) String roundName, @RequestParam(required = false) String industryName , @RequestParam(required = false) String cityName, @RequestParam(required = false) String educationName, @RequestParam(required = false) String workName, @RequestParam(required = false,defaultValue = "0") Integer from, @RequestParam(required = false, defaultValue = "10") Integer size){

        StatisticsDto<List<HistogramList>> result = null;

        try {
            result = evaluateService.financingAmount(investment,roundName,industryName,cityName,educationName,workName,from,size);
        } catch (Exception e) {
            result = new StatisticsDto<>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    @PostMapping("financing/amount/list")
    public StatisticsDto<List<HistogramList>> financingAmountPost(@RequestBody Map<String, String> body, @RequestParam(required = false,defaultValue = "0") Integer from, @RequestParam(required = false, defaultValue = "10") Integer size){

        StatisticsDto<List<HistogramList>> result = null;

        try {
            result = evaluateService.financingAmount(body.get("investment"), body.get("roundName"),body.get("industryName"),body.get("cityName"),body.get("educationName"),body.get("workName"),from,size);
        } catch (Exception e) {
            e.printStackTrace();
            result = new StatisticsDto<>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    /**
     * 估值分布
     * @param investment 投资机构（1：50机构，-1：非50机构）
     * @param roundName  轮次名称
     * @param industryName 行业领域名称
     * @param cityName  城市名称
     * @param educationName 教育背景
     * @param workName 工作背景
     * @return
     */
    @GetMapping("valuation/list")
    public StatisticsDto<List<HistogramList>> valuation(@RequestParam(required = false,defaultValue = "-1") String investment,@RequestParam(required = false) String roundName, @RequestParam(required = false) String industryName , @RequestParam(required = false) String cityName, @RequestParam(required = false) String educationName, @RequestParam(required = false) String workName, @RequestParam(required = false,defaultValue = "0") Integer from, @RequestParam(required = false, defaultValue = "10") Integer size ){

        StatisticsDto<List<HistogramList>> result = null;
        try {
            result = evaluateService.valuation(investment,roundName,industryName,cityName,educationName,workName,from,size);
        } catch (Exception e) {
            result = new StatisticsDto<>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    @PostMapping("valuation/list")
    public StatisticsDto<List<HistogramList>> valuation(@RequestBody Map<String,String> body, @RequestParam(required = false,defaultValue = "0") Integer from, @RequestParam(required = false, defaultValue = "10") Integer size ){

        StatisticsDto<List<HistogramList>> result = null;
        try {
            result = evaluateService.valuation(body.get("investment"), body.get("roundName"),body.get("industryName"),body.get("cityName"),body.get("educationName"),body.get("workName"),from,size);
        } catch (Exception e) {
            result = new StatisticsDto<>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }


    //    @GetMapping("users/list")
//    @GetMapping("users/{id}")
    @GetMapping("users/list")
    public StatisticsDto<String> users(@RequestParam String name, @RequestParam Integer id){
        log.info("name={}",name);
        log.info("id={}", id);

        StatisticsDto<String> result = new StatisticsDto<String>();
        result.setData("Hello World");
        result.setStatus(200);
        result.setMessage("success.");

        return result;
    }


    @PostMapping("users/post")
    public StatisticsDto<String> users(@RequestBody Map<String, Object> body){

        log.info("name={}",body.get("name"));
        log.info("id={}", body.get("id"));

        StatisticsDto<String> result = new StatisticsDto<String>();
        result.setData("Hello World");
        result.setStatus(200);
        result.setMessage("success.");


        return result;

    }
}
