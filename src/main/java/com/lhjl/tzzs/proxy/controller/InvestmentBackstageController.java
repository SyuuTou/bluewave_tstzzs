package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.service.InvestmentBackstageService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.lhjl.tzzs.proxy.dto.CommonDto;

import javax.annotation.Resource;

@RestController
public class InvestmentBackstageController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentDataController.class);

    @Resource
    private InvestmentBackstageService investmentBackstageService;

    /**
     * 管理员创建机构接口
     * @param body
     * @return
     */
    @PostMapping("admin/cinvestment/backstage")
    public CommonDto<String> investmentBackstageAdd(@RequestBody InvestmentInstitutions body){
        CommonDto<String> result = new CommonDto<String>();

        try {
            result = investmentBackstageService.adminAddInvestmentBackstage(body);
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);

            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
}
