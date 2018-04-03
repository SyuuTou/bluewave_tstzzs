package com.lhjl.tzzs.proxy.controller.investor.palatform.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorInvestInfoDto;
import com.lhjl.tzzs.proxy.service.InvestorInvestInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author caochuangui
 * @date 2018-1-30 17:26:54
 */
@RestController
public class InvestorDemandInfoController extends GenericController{

    @Resource
    private InvestorInvestInfoService investorInvestInfoService;

    /**
     * 编辑投资人投资偏好信息
     */
    @PostMapping("/addorupdateinvestorinvestinfo")
    public CommonDto<String> addOrUpdateInvestorInvestInfo(@RequestBody InvestorInvestInfoDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = investorInvestInfoService.addOrUpdateInvestorInvestInfo(body);
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
    @GetMapping("/getinvestorinvestinfo")
    public CommonDto<InvestorInvestInfoDto> getInvestorInvestInfo(Integer investorId){
        CommonDto<InvestorInvestInfoDto> result = new CommonDto<>();
        try {
            result = investorInvestInfoService.getInvestorInvestInfo(investorId);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }


}
