package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionComplexOutputDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionSearchOutputDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InvestmentInstitutionsServiceImpl implements InvestmentInstitutionsService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentInstitutionsServiceImpl.class);

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Override
    public CommonDto<InvestmentInstitutionComplexOutputDto> getInvestmentInstitutionsComlexInfo(Map<String,Integer> body){
        CommonDto<InvestmentInstitutionComplexOutputDto> result =  new CommonDto<>();

        if (body.get("investorInstitutionId") == null){
            result.setStatus(50001);
            result.setMessage("机构id不能为空");
            result.setData(null);

            log.info("根据id获取机构信息的接口场景");
            log.info("机构id不能为空");

            return result;
        }

        InvestmentInstitutions investmentInstitutions = investmentInstitutionsMapper.selectByPrimaryKey(body.get("investorInstitutionId"));
        if (investmentInstitutions == null){
            result.setMessage("当前机构id没有找到对应的机构");
            result.setStatus(50001);
            result.setData(null);

            log.info("根据id获取机构信息的接口场景");
            log.info("当前机构id没有找到对应的机构");

            return result;
        }else {
            InvestmentInstitutionComplexOutputDto investmentInstitutionComplexOutputDto = new InvestmentInstitutionComplexOutputDto();
            investmentInstitutionComplexOutputDto.setInvestmentInstitutionDesc(investmentInstitutions.getKenelCase());
            investmentInstitutionComplexOutputDto.setInvestmentInstitutionLogo(investmentInstitutions.getLogo());
            investmentInstitutionComplexOutputDto.setInvestmentInstitutionName(investmentInstitutions.getShortName());

            result.setData(investmentInstitutionComplexOutputDto);
            result.setMessage("success");
            result.setStatus(200);

        }



        return result;
    }

    /**
     * 根据输入词搜索机构信息接口
     * @param inputsWords 输入的词
     * @return
     */
    @Override
    public CommonDto<List<InvestmentInstitutionSearchOutputDto>> getInstitutionIntelligentSearch(String inputsWords,Integer pageSize){
        CommonDto<List<InvestmentInstitutionSearchOutputDto>> result = new CommonDto<>();
        List<InvestmentInstitutionSearchOutputDto> list = new ArrayList<>();

        if (inputsWords == null || "undefined".equals(inputsWords)){
           inputsWords = "";
        }
        if (pageSize == null || pageSize <= 0){
            pageSize = 5;
        }


        List<InvestmentInstitutions> investmentInstitutionsList = investmentInstitutionsMapper.findeInvestmentByShortName(inputsWords,0,pageSize);
        if (investmentInstitutionsList.size() > 0){
            for(InvestmentInstitutions ii:investmentInstitutionsList){
                InvestmentInstitutionSearchOutputDto investmentInstitutionSearchOutputDto = new InvestmentInstitutionSearchOutputDto();
                investmentInstitutionSearchOutputDto.setInstitutionFullName(ii.getShortName());
                investmentInstitutionSearchOutputDto.setInstitutionShortName(ii.getShortName());
                investmentInstitutionSearchOutputDto.setInstitutionId(ii.getId());

                list.add(investmentInstitutionSearchOutputDto);
            }

            result.setData(list);
            result.setMessage("success");
            result.setStatus(200);
        }else {
            result.setStatus(200);
            result.setMessage("success");
            result.setData(list);
        }

        return result;
    }
}
