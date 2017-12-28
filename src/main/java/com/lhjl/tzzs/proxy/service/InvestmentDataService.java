package com.lhjl.tzzs.proxy.service;
import com.lhjl.tzzs.proxy.dto.CommonDto;

import java.util.Date;

public interface InvestmentDataService {
    CommonDto<String> addInvestmentData(String investment_institution_name , String project_name, String project_full_name, String summary, String field, String city, String rounds, String amount, String currency, String stock_right, Date date, String founder_name, String founder_work, String founder_education,String userId);
}
