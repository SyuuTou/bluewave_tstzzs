package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionComplexOutputDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionSearchOutputDto;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class InvestmentInstitutionsController {

    private static final Logger log = LoggerFactory.getLogger(InvestmentInstitutionsController.class);

    @Resource
    private InvestmentInstitutionsService investmentInstitutionsService;

    /**
     * 根据机构id获取机构信息的接口
     * @param body
     * @return
     */
    @PostMapping("get/investmentinstitutons/complexinfo")
    public CommonDto<InvestmentInstitutionComplexOutputDto> getInvestmentInstitutionsComplexinfo(@RequestBody Map<String,Integer> body){
        CommonDto<InvestmentInstitutionComplexOutputDto> result = new CommonDto<>();

        try {
            result =  investmentInstitutionsService.getInvestmentInstitutionsComlexInfo(body);

        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }

        return result;
    }

    /**
     * 根据输入词搜索机构信息接口
     * @param inputsWords 输入的词
     * @return
     */
    @GetMapping("institutions/intelligent/search")
    public CommonDto<List<InvestmentInstitutionSearchOutputDto>> institutionsIntelligentSearch(String inputsWords,Integer pageSize){
        CommonDto<List<InvestmentInstitutionSearchOutputDto>> result =new CommonDto<>();

        try {
            result = investmentInstitutionsService.getInstitutionIntelligentSearch(inputsWords,pageSize);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());

            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }

    @GetMapping("institutions/details")
    public CommonDto<Map<String,Object>> findInstitutionDetails(Integer institutionId){
        CommonDto<Map<String,Object>> result  = new CommonDto<>();

        try {
            result = investmentInstitutionsService.findInstitutionDetails(institutionId);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }
}
