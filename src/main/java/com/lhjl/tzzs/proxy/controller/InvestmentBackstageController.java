package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.PageInfoDto;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.service.InvestmentBackstageService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.lhjl.tzzs.proxy.dto.CommonDto;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取所有机构数据（50与非50）
     * @return
     */
    @GetMapping("find/investment")
    public CommonDto<List<Map<String, Object>>> findAllInvestment(){
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
        try {
            result = investmentBackstageService.findAllInvestment();
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);

            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 查询50机构（分页）
     * @return
     */
    @PostMapping("find/investment/five")
    public CommonDto<List<InvestmentInstitutions>> findFiveInvestment(@RequestBody PageInfoDto body){
        CommonDto<List<InvestmentInstitutions>> result = new CommonDto<List<InvestmentInstitutions>>();
        try {
            result = investmentBackstageService.findFiveInvestment(body.getPageNum(), body.getPageSize());
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 查询非50机构（分页）
     * @return
     */
    @PostMapping("find/investment/notfive")
    public CommonDto<List<InvestmentInstitutions>> findNotFiveInvestment(@RequestBody PageInfoDto body){
        CommonDto<List<InvestmentInstitutions>> result = new CommonDto<List<InvestmentInstitutions>>();
        try {
            result = investmentBackstageService.findNotFiveInvestment(body.getPageNum(), body.getPageSize());
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
}
