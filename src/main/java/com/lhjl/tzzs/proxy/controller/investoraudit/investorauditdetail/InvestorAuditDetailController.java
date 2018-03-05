package com.lhjl.tzzs.proxy.controller.investoraudit.investorauditdetail;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorKernelInfoDto;
import com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditdetaildto.*;
import com.lhjl.tzzs.proxy.service.InvestorAuditService;
import com.lhjl.tzzs.proxy.service.InvestorBasicinfoService;
import com.lhjl.tzzs.proxy.service.InvestorInfoService;
import com.lhjl.tzzs.proxy.service.InvestorInvestInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/7.
 */
@RestController
public class InvestorAuditDetailController extends GenericController{


    @Resource
    private InvestorAuditService investorAuditService;

    /**
     * 编辑投资人个人信息
     * @param body
     * @return
     */
    @PostMapping("/addorupdateinvestorauditinfo")
    public CommonDto<String> addOrUpdateInvestorAuditInfo(@RequestBody InvestorAuditInfoInputDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = investorAuditService.addOrUpdateInvestorAuditInfo(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 得到投资人信息
     * @param investorId
     * @return
     */
    @GetMapping("/getinvestorauditinfo")
    public CommonDto<InvestorAuditInfoOutputDto> getInvestorAuditInfo(Integer investorId){
        CommonDto<InvestorAuditInfoOutputDto> result = new CommonDto<>();
        try {
            result = investorAuditService.getInvestorAuditInfo(investorId);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    @PostMapping("/addorupdateinvestorauditbasicinfo")
    public CommonDto<String> addOrUpdateInvestorAuditBasicInfo(@RequestBody InvestorAuditBasicInfoInputDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = investorAuditService.addOrUpdateInvestorAuditBasicInfo(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    @GetMapping("/getinvestorauditbasicinfo")
    public CommonDto<InvestorAuditBasicInfoOutputDto> getInvestorAuditBasicInfo(Integer investorId){
        CommonDto<InvestorAuditBasicInfoOutputDto> result = new CommonDto<>();
        try {
            result = investorAuditService.getInvestorAuditBasicInfo(investorId);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    @GetMapping("/getinvestorauditintroduction")
    public CommonDto<InvestorAuditIntroductionOutputDto> getInvestorAuditIntroduction(Integer investorId){
        CommonDto<InvestorAuditIntroductionOutputDto> result = new CommonDto<>();
        try {
            result = investorAuditService.getInvestorAuditIntroduction(investorId);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    @PostMapping("/addorupdateinvestorauditintroduction")
    public CommonDto<String> addOrUpdateInvestorAuditIntroduction(@RequestBody InvestorAuditIntroductionInputDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = investorAuditService.addOrUpdateInvestorAuditIntroduction(body);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }


    /**
     * 编辑投资人投资偏好信息
     */
    @PostMapping("/addorupdateinvestorauditinvestinfo")
    public CommonDto<String> addOrUpdateInvestorAuditDemand(@RequestBody InvestorAuditDemandInputDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = investorAuditService.addOrUpdateInvestorAuditDemand(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 得到投资人偏好信息
     * @param investorId
     * @return
     */
    @GetMapping("/getinvestorauditinvestinfo")
    public CommonDto<InvestorAuditDemandOutputDto> getInvestorAuditDemand(Integer investorId){
        CommonDto<InvestorAuditDemandOutputDto> result = new CommonDto<>();
        try {
            result = investorAuditService.getInvestorAuditDemand(investorId);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    @GetMapping("/getmatchinvestor")
    public CommonDto<Map<String, Object>> getMatchInvestor(String investorName){
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        try {
            result = investorAuditService.getMatchInvestor(investorName);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

}
