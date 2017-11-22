package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto;
import com.lhjl.tzzs.proxy.dto.SaveScreenDto;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.SearchInvestmentRecord;
import java.util.List;
import java.util.Map;

/**
 * Created by lmy on 2017/11/20.
 */
public interface ScreenAndSearchInstitutionService {
    CommonDto<List<InvestmentInstitutionsDto>> searchInstitution(String shortName, Integer pageNum, Integer pageSize, String token);
    CommonDto<List<SearchInvestmentRecord>> searchInstitutionRecord(String token);
    CommonDto<List<SearchInvestmentRecord>> searchInstitutionHot();
    CommonDto<Map<String, Object>> screenInstitution(String token);
    CommonDto<String>  savescreenInstitution(SaveScreenDto body);
    CommonDto<List<InvestmentInstitutionsDto>> screenInstitutionAll(SaveScreenDto body);
}
