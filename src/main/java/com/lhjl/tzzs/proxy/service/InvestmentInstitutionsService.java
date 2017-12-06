package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionComplexOutputDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionSearchOutputDto;

import java.util.List;
import java.util.Map;

public interface InvestmentInstitutionsService {
    /**
     * 根据机构id获取机构信息的接口
     * @param body
     * @return
     */
    CommonDto<InvestmentInstitutionComplexOutputDto> getInvestmentInstitutionsComlexInfo(Map<String,Integer> body);

    /**
     * 根据输入词搜索机构信息接口
     * @param inputsWords 输入的词
     * @return
     */
    CommonDto<List<InvestmentInstitutionSearchOutputDto>> getInstitutionIntelligentSearch(String inputsWords,Integer pageSize);

    /**
     *  读取机构详情的接口
     * @param institutionId
     * @return
     */
    CommonDto<Map<String,Object>> findInstitutionDetails(Integer institutionId);
}
