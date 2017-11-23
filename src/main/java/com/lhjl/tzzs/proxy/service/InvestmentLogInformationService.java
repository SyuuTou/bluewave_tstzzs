package com.lhjl.tzzs.proxy.service;

import java.util.Date;
import java.util.Map;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentLogInformationDto;

/**
 * Created by lmy on 2017/11/23.
 */
public interface InvestmentLogInformationService {
    CommonDto<String> saveInvestmentLogInformation(InvestmentLogInformationDto params);
    CommonDto<Map<String,Object>> findinvestmentlog(InvestmentLogInformationDto body);

    CommonDto<Map<String, Object>> findinformationnew(Integer id);

    CommonDto<String> saveInvestmentData1new(String project_name, String project_full_name, String summary, String field, String city, String rounds, String amount, String currency, String stock_right, Date dateDate, String founder_name, String founder_work, String founder_education, String userId, String zhiwu, Integer id);

    CommonDto<String> saveInvestmentDatanew(String project_name, String project_full_name, String summary, String field, String city, String rounds, String amount, String currency, String stock_right, Date dateDate, String founder_name, String founder_work, String founder_education, String userId, String zhiwu);

}
