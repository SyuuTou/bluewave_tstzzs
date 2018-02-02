package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorCertificationDto;
import com.lhjl.tzzs.proxy.service.InvestorCertificationInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author caochuangui
 * @date 2018-1-30 17:24:30
 */
@RestController
public class InvestorCertificationInfoController extends GenericController{

    @Resource
    private InvestorCertificationInfoService investorCertificationInfoService;

    /**
     * 编辑投资人个人信息
     * @param body
     * @return
     */
    @PostMapping("/addorupdateinvestorcertification")
    public CommonDto<String> addOrUpdateInvestorCertification(@RequestBody InvestorCertificationDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = investorCertificationInfoService.addOrUpdateInvestorCertification(body);
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
    @GetMapping("/getinvestorcertification")
    public CommonDto<InvestorCertificationDto> getInvestorCertification(Integer investorId){
        CommonDto<InvestorCertificationDto> result = new CommonDto<>();
        try {
            result = investorCertificationInfoService.getInvestorCertification(investorId);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
