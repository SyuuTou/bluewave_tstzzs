package com.lhjl.tzzs.proxy.controller.investor.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorKernelInfoDto;
import com.lhjl.tzzs.proxy.service.InvestorInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author caochuangui
 * @date 2018-1-30 17:25:00
 */
@RestController
public class InvestorInfoController extends GenericController{

    @Resource
    private InvestorInfoService investorInfoService;

    /**
     * 编辑投资人个人信息
     * @param body
     * @return
     */
    @PostMapping("/addorupdateinvestorinfo")
    public CommonDto<Integer> addOrUpdateInvestorInfo(@RequestBody InvestorKernelInfoDto body){
        CommonDto<Integer> result = new CommonDto<>();
        try {
            result = investorInfoService.addOrUpdateInvestorInfo(body);
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
    @GetMapping("/getinvestorinfo")
    public CommonDto<InvestorKernelInfoDto> getInvestorInfo(Integer investorId){
        CommonDto<InvestorKernelInfoDto> result = new CommonDto<>();
        try {
            result = investorInfoService.getInvestorInfo(investorId);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
