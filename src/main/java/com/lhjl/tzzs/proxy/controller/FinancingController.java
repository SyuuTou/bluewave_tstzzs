package com.lhjl.tzzs.proxy.controller;


import com.lhjl.tzzs.proxy.dto.CommonDto;
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
    public CommonDto<Map<String,List<LabelList>>> hotData(){
        CommonDto<Map<String,List<LabelList>>> result = null;
        try {
            result = evaluateService.queryHotData();
        }catch (Exception e){
            result = new CommonDto<Map<String, List<LabelList>>>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
}

    @GetMapping("hotIndustry")
    public CommonDto<List<LabelList>> hotIndustry(){
        CommonDto<List<LabelList>> result = null;
        try {
            result = evaluateService.queryHotIndustry();
        }catch (Exception e){
            result = new CommonDto< List<LabelList>>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 融资金额
     * @param roundName  轮次名称
     * @param industryName 行业领域名称
     * @param cityName  城市名称
     * @param educationName 教育背景
     * @param workName 工作背景
     * @return
     */
    @GetMapping("financing/amount/list")
    public CommonDto<List<HistogramList>> financingAmount(@RequestParam(required = false) String roundName, @RequestParam(required = false) String industryName , @RequestParam(required = false) String cityName, @RequestParam(required = false) String educationName, @RequestParam(required = false) String workName, @RequestParam(required = false,defaultValue = "0") Integer from, @RequestParam(required = false, defaultValue = "10") Integer size){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = evaluateService.financingAmount(roundName,industryName,cityName,educationName,workName,from,size);
        } catch (Exception e) {
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    @PostMapping("financing/amount/list")
    public CommonDto<List<HistogramList>> financingAmountPost(@RequestBody Map<String, String> body, @RequestParam(required = false,defaultValue = "0") Integer from, @RequestParam(required = false, defaultValue = "10") Integer size){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = evaluateService.financingAmount(body.get("roundName"),null,null,null,null,from,size);
        } catch (Exception e) {
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    /**
     * 估值分布
     * @param roundName  轮次名称
     * @param industryName 行业领域名称
     * @param cityName  城市名称
     * @param educationName 教育背景
     * @param workName 工作背景
     * @return
     */
    @GetMapping("valuation/list")
    public CommonDto<List<HistogramList>> valuation(@RequestParam(required = false) String roundName, @RequestParam(required = false) String industryName , @RequestParam(required = false) String cityName, @RequestParam(required = false) String educationName, @RequestParam(required = false) String workName, @RequestParam(required = false,defaultValue = "0") Integer from, @RequestParam(required = false, defaultValue = "10") Integer size ){

        CommonDto<List<HistogramList>> result = null;
        try {
            result = evaluateService.valuation(roundName,industryName,cityName,educationName,workName,from,size);
        } catch (Exception e) {
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    @PostMapping("valuation/list")
    public CommonDto<List<HistogramList>> valuation(@RequestBody Map<String,String> req, @RequestParam(required = false,defaultValue = "0") Integer from, @RequestParam(required = false, defaultValue = "10") Integer size ){

        CommonDto<List<HistogramList>> result = null;
        try {
            result = evaluateService.valuation(req.get("roundName"),null,null,null,null,from,size);
        } catch (Exception e) {
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }


    //    @GetMapping("users/list")
//    @GetMapping("users/{id}")
    @GetMapping("users/list")
    public CommonDto<String> users(@RequestParam String name, @RequestParam Integer id){
        log.info("name={}",name);
        log.info("id={}", id);

        CommonDto<String> result = new CommonDto<String>();
        result.setData("Hello World");
        result.setStatus(200);
        result.setMessage("success.");

        return result;
    }


    @PostMapping("users/post")
    public CommonDto<String> users(@RequestBody Map<String, Object> body){

        log.info("name={}",body.get("name"));
        log.info("id={}", body.get("id"));

        CommonDto<String> result = new CommonDto<String>();
        result.setData("Hello World");
        result.setStatus(200);
        result.setMessage("success.");


        return result;

    }
}
