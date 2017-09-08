package com.lhjl.tzzs.proxy.controller;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@RestController
public class FinancingController {
    private static final Logger log = LoggerFactory.getLogger(FinancingController.class);


    @Resource(name = "evaluateService")
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
    @PostMapping("financing/amount/list")
    public CommonDto<List<HistogramList>> financingAmount(@RequestParam(required = false) String roundName, @RequestParam(required = false) String industryName , @RequestParam(required = false) String cityName, @RequestParam(required = false) String educationName, @RequestParam(required = false) String workName){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = evaluateService.financingAmount(roundName,industryName,cityName,educationName,workName);
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
    public CommonDto<List<HistogramList>> valuation(@RequestParam(required = false) String roundName, @RequestParam(required = false) String industryName , @RequestParam(required = false) String cityName, @RequestParam(required = false) String educationName, @RequestParam(required = false) String workName){

        CommonDto<List<HistogramList>> result = null;
        try {
            result = evaluateService.valuation(roundName,industryName,cityName,educationName,workName);
        } catch (Exception e) {
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(510);
            result.setMessage("数据检索异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }
}
