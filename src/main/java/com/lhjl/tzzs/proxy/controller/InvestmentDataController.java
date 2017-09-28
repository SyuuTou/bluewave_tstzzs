package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.InvestmentDataDto;
import com.lhjl.tzzs.proxy.service.InvestmentDataService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class InvestmentDataController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentDataController.class);

    @Resource
    private InvestmentDataService investmentDataService;

    /**
     * 创建提交投资数据接口
     * @param body
     * @return
     */
    @PostMapping("investment/cdata")
    public CommonDto<String> userSubmitInvestmentData(@RequestBody InvestmentDataDto body){
        CommonDto<String> result = new CommonDto<String>();

        String investment_institution_name = body.getInvestment_institution_name();
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



        try {
          SimpleDateFormat simpleDateFormat =new SimpleDateFormat("YYYY-MM-DD");
          Date dateDate = new Date();
          if (!StringUtil.isEmpty(date)) {
               dateDate = simpleDateFormat.parse(date);
          }
          result = investmentDataService.addInvestmentData(investment_institution_name,project_name,project_full_name,summary,field,city,rounds,amount,currency,stock_right,dateDate,founder_name,founder_work,founder_education);
      }catch (Exception e){
          result.setMessage(e.getMessage());
          result.setStatus(501);
          result.setData(null);
          log.error(e.getMessage(),e.fillInStackTrace());
      }


        return result;
    }

}
