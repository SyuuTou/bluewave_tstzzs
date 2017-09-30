package com.lhjl.tzzs.proxy.controller;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.service.StatisticsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public CommonDto<List<HistogramList>> financing50CountDistributed(@PathVariable String institutionType){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingCountDistributed(institutionType);

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
    public CommonDto<List<HistogramList>> financing50amountDistributed(@PathVariable String institutionType){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingAmountDistributed( institutionType);

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
    public CommonDto<List<HistogramList>> financing50SegmentationDistributed(@PathVariable String institutionType){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingSegmentationDistributed( institutionType);

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
    public CommonDto<List<HistogramList>> financing50CityDistributed(@PathVariable String institutionType){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingCityDistributed( institutionType);

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
    public CommonDto<List<HistogramList>> financing50EducationExperienceDistributed(){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingEducationExperienceDistributed();

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
    public CommonDto<List<HistogramList>> financing50WorkExperienceDistributed(){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingWorkExperienceDistributed();

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
    public CommonDto<List<HistogramList>> financing50InvestmentDistributed(){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingInvestmentDistributed();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(50001);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
