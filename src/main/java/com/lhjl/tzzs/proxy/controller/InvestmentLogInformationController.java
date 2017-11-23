package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.service.InvestmentLogInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by lmy on 2017/11/23.
 */
@RestController
public class InvestmentLogInformationController {

    private static Logger logger = LoggerFactory.getLogger(InvestmentLogInformationController.class);

    @Autowired
    private InvestmentLogInformationService investmentLogInformationService;

    /**
     * 保存机构信息
     * @param params
     * @return
     */
    @PostMapping("save/investment/log/information")
    public CommonDto<String> saveInvestmentLogInformation(@RequestBody InvestmentLogInformationDto params){
        CommonDto<String> result = new CommonDto<>();
        try{

            result = investmentLogInformationService.saveInvestmentLogInformation(params);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }

        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("服务器发生异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 机构信息的回显
     * @param body
     * @return
     */
    @PostMapping("find/investment/log/information")
    public CommonDto<Map<String,Object>> findinvestmentlog(@RequestBody InvestmentLogInformationDto body){
        CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();
        try {
                result = investmentLogInformationService.findinvestmentlog(body);
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
    /**
     * 创建提交投资数据接口 /新
     * @param body
     * @return
     */
    @PostMapping("investment/data/log/new")
    public CommonDto<String> saveInvestmentDatanew(@RequestBody InvestmentDataDto body){
        CommonDto<String> result = new CommonDto<String>();

    /*    String investment_institution_name = body.getInvestment_institution_name();*/
        String project_name = body.getProject_name();
        String project_full_name = body.getProject_full_name();
        String summary = body.getSummary();
        String field = body.getField();
        String city =body.getCity();
        String rounds = body.getRounds();
        String amount = body.getAmount();
        String currency = body.getCurrency();
        String stock_right = body.getStock_right();
        String date = body.getDate();
        String founder_name = body.getFounder_name();
        String founder_work = body.getFounder_work();
        String founder_education = body.getFounder_education();
        String userId = body.getUserId();
        String zhiwu=body.getZhiwu();



        try {
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
            Date dateDate = null;
            if (!StringUtil.isEmpty(date)) {
                dateDate = simpleDateFormat.parse(date);
            }
            result = investmentLogInformationService.saveInvestmentDatanew(project_name,project_full_name,summary,field,city,rounds,amount,currency,stock_right,dateDate,founder_name,founder_work,founder_education,userId,zhiwu);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);
            result.setData(null);
            logger.error(e.getMessage(),e.fillInStackTrace());
        }


        return result;
    }
    /**
     * 数据的回显/新
     * @param id
     * @return
     */
    @GetMapping("information/record/new")
    public CommonDto<Map<String, Object>> findinformationnew(Integer id){
        CommonDto<Map<String, Object>>  result = new CommonDto<Map<String, Object>>();

        try {
            result =investmentLogInformationService.findinformationnew(id);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("显示页面异常，请稍后再试");
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * 修改页面/新
     * @param body
     * @return
     */
    @PostMapping("investment/log/new")
    public CommonDto<String> saveInvestmentData1new(@RequestBody InvestmentDatalogDto body){
        CommonDto<String> result = new CommonDto<String>();

        String project_name = body.getProject_name();
        String project_full_name = body.getProject_full_name();
        String summary = body.getSummary();
        String field = body.getField();
        String city =body.getCity();
        String rounds = body.getRounds();
        String amount = body.getAmount();
        String currency = body.getCurrency();
        String stock_right = body.getStock_right();
        String date = body.getDate();
        String founder_name = body.getFounder_name();
        String founder_work = body.getFounder_work();
        String founder_education = body.getFounder_education();
        String userId = body.getUserId();
        String zhiwu=body.getZhiwu();
        Integer id =body.getId();



        try {
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
            Date dateDate = null;
            if (!StringUtil.isEmpty(date)) {
                dateDate = simpleDateFormat.parse(date);
            }
            result = investmentLogInformationService.saveInvestmentData1new(project_name,project_full_name,summary,field,city,rounds,amount,currency,stock_right,dateDate,founder_name,founder_work,founder_education,userId,zhiwu,id);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);
            result.setData(null);
            logger.error(e.getMessage(),e.fillInStackTrace());
        }


        return result;
    }
}
