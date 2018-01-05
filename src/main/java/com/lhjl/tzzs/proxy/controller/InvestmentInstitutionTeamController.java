package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionTeamDto;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionTeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class InvestmentInstitutionTeamController extends GenericService{

    @Resource
    private InvestmentInstitutionTeamService investmentInstitutionTeamService;

    @GetMapping("institution/team/list")
    public CommonDto<List<InvestmentInstitutionTeamDto>> getInstitutionTeamList(Integer institutionId){
        CommonDto<List<InvestmentInstitutionTeamDto>> result = new CommonDto<>();
        try {
            result = investmentInstitutionTeamService.getInvestmentInstitutionById(institutionId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
        }

        return result;
    }
}
