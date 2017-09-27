package com.lhjl.tzzs.proxy.controller;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public CommonDto<List<HistogramList>> financing50CountDistributed(String institutionType){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingCountDistributed( institutionType);

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
    public CommonDto<List<HistogramList>> financing50amountDistributed(String institutionType){

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

    @GetMapping("financing/{institutionType}/segmentation/distributed")
    public CommonDto<List<HistogramList>> financing50SegmentationDistributed(String institutionType){

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

    @GetMapping("financing/{institutionType}/city/distributed")
    public CommonDto<List<HistogramList>> financing50CityDistributed(String institutionType){

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

    @GetMapping("financing/{institutionType}/educationExperience/distributed")
    public CommonDto<List<HistogramList>> financing50EducationExperienceDistributed(String institutionType){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingEducationExperienceDistributed( institutionType);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(50001);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @GetMapping("financing/{institutionType}/workExperience/distributed")
    public CommonDto<List<HistogramList>> financing50WorkExperienceDistributed(String institutionType){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingWorkExperienceDistributed( institutionType);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(50001);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @GetMapping("financing/{institutionType}/investment/distributed")
    public CommonDto<List<HistogramList>> financing50InvestmentDistributed(String institutionType){

        CommonDto<List<HistogramList>> result = null;

        try {
            result = statisticsService.financingInvestmentDistributed( institutionType);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<List<HistogramList>>();
            result.setStatus(50001);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
