package com.lhjl.tzzs.proxy.controller.investor.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorBasicInfoInputDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorBasicInfoOutputDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorIntroductionDto;
import com.lhjl.tzzs.proxy.model.MetaDiploma;
import com.lhjl.tzzs.proxy.model.MetaRegion;
import com.lhjl.tzzs.proxy.service.InvestorBasicinfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author caochuangui'
 * @date 2018-1-30 17:24:11
 */
@RestController
public class InvestorPersonalIntroductionController extends GenericController{

    @Resource
    private InvestorBasicinfoService investorBasicinfoService;

    /**
     * 获取投资人个人简介
     * @param investorId
     * @return
     */
    @GetMapping("/getinvestorintroduction")
    public CommonDto<InvestorIntroductionDto> getInvestorIntroduction(Integer investorId){
        CommonDto<InvestorIntroductionDto> result = new CommonDto<>();
        try {
            result = investorBasicinfoService.getInvestorIntroduction(investorId);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
    /**
     * 保存或者更新投资人的个人简介
     * @param body
     * @return
     */
    @PostMapping("/addorupdateinvestorintroduction")
    public CommonDto<String> addOrUpdateInvestorIntroduction(@RequestBody InvestorIntroductionDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = investorBasicinfoService.addOrUpdateInvestorIntroduction(body);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

}
