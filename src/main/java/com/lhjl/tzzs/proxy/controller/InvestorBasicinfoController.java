package com.lhjl.tzzs.proxy.controller;

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
public class InvestorBasicinfoController extends GenericController{

    @Resource
    private InvestorBasicinfoService investorBasicinfoService;

    //TODO 教育经历智能检索

    //TODO 工作经历智能检索

    //TODO 创业经历智能检索

    //TODO 自定义城市智能检索

    @PostMapping("/addorupdateinvestorbasicinfo")
    public CommonDto<String> addOrUpdateInvestorBasicInfo(@RequestBody InvestorBasicInfoInputDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = investorBasicinfoService.addOrUpdateInvestorBasicInfo(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    @GetMapping("/getinvestorbasicinfo")
    public CommonDto<InvestorBasicInfoOutputDto> getInvestorBasicInfo(Integer investorId){
        CommonDto<InvestorBasicInfoOutputDto> result = new CommonDto<>();
        try {
            result = investorBasicinfoService.getInvestorBasicInfo(investorId);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

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


    @GetMapping("/getallcontry")
    public CommonDto<List<MetaRegion>> getAllContry(){
        CommonDto<List<MetaRegion>> result = new CommonDto<>();
        try {
            result = investorBasicinfoService.getAllContry();
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    @GetMapping("/getalldiploma")
    public CommonDto<List<MetaDiploma>> getAllDiploma(){
        CommonDto<List<MetaDiploma>> result = new CommonDto<>();
        try {
            result = investorBasicinfoService.getAllDiploma();
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

}
