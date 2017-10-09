package com.lhjl.tzzs.proxy.controller;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.service.StatisticsService;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StatisticsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsController.class);

    @Resource(name = "statisticsService")
    private StatisticsService statisticsService;

    /**
     * 每月投资数量分布
     * @param institutionType 机构类型（50和NONE）
     * @return
     */
    @GetMapping("financing/{institutionType}/count/distributed")
    public CommonDto<List<HistogramList>> financing50CountDistributed(@PathVariable String institutionType , @RequestParam( required=false, defaultValue= "0") String from,@RequestParam(required = false,defaultValue = "10") String size){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingCountDistributed(institutionType,from,size);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(50001);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    /**
     * 每月投资金额分布
     * @param institutionType 机构类型（50和NONE）
     * @return
     */
    @GetMapping("financing/{institutionType}/amount/distributed")
    public CommonDto<List<HistogramList>> financing50amountDistributed(@PathVariable String institutionType, @RequestParam( required=false, defaultValue= "0") String from,@RequestParam(required = false,defaultValue = "10") String size){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingAmountDistributed( institutionType,from,size);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(50001);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    /**
     * 热门投资领域
     * @param institutionType 机构类型（50和NONE）
     * @return
     */
    @GetMapping("financing/{institutionType}/segmentation/distributed")
    public CommonDto<List<HistogramList>> financing50SegmentationDistributed(@PathVariable String institutionType, @RequestParam( required=false, defaultValue= "0") String from,@RequestParam(required = false,defaultValue = "10") String size){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingSegmentationDistributed( institutionType,from,size);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(50001);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    /**
     * i.	热门投资地域
     * @param institutionType 机构类型（50和NONE）
     * @return
     */
    @GetMapping("financing/{institutionType}/city/distributed")
    public CommonDto<List<HistogramList>> financing50CityDistributed(@PathVariable String institutionType, @RequestParam( required=false, defaultValue= "0") String from,@RequestParam(required = false,defaultValue = "10") String size){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingCityDistributed( institutionType,from,size);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(50001);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    /**
     * i.	热门投资创始人教育背景
     * @return
     */
    @GetMapping("financing/educationExperience/distributed")
    public CommonDto<List<HistogramList>> financing50EducationExperienceDistributed( @RequestParam( required=false, defaultValue= "0") String from,@RequestParam(required = false,defaultValue = "10") String size){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingEducationExperienceDistributed(from,size);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(50001);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    /**
     * i.	热门投资创始人工作背景
     * @return
     */
    @GetMapping("financing/workExperience/distributed")
    public CommonDto<List<HistogramList>> financing50WorkExperienceDistributed( @RequestParam( required=false, defaultValue= "0") String from,@RequestParam(required = false,defaultValue = "10") String size){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingWorkExperienceDistributed(from,size);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(50001);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    /**
     * 热门投资分布
     * @return
     */
    @GetMapping("financing/investment/distributed")
    public CommonDto<List<HistogramList>> financing50InvestmentDistributed( @RequestParam( required=false, defaultValue= "0") String from,@RequestParam(required = false,defaultValue = "10") String size){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingInvestmentDistributed(from,size);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(50001);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
