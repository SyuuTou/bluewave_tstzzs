package com.lhjl.tzzs.proxy.controller.investor.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorKernelInfoDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorOperateLogDto;
import com.lhjl.tzzs.proxy.service.InvestorProcessTrackingService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-30 17:28:53
 */
@RestController
public class InvestorProcessTrackingController extends GenericController{

    @Resource
    private InvestorProcessTrackingService investorProcessTrackingService;

    /**
     * 添加或编辑操作日志表
     * @param body
     * @return
     */
    @PostMapping("/addorupdateoperateLog")
    public CommonDto<String> addorupdateOperateLog(@RequestBody InvestorOperateLogDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = investorProcessTrackingService.addorupdateOperateLog(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 得到操作日志表
     * @param investorId
     * @return
     */
    @Transactional(readOnly = true)
    @GetMapping("/getinvestoroperateLoglist")
    public CommonDto<List<InvestorOperateLogDto>> getInvestorOperateLogList(Integer investorId){
        CommonDto<List<InvestorOperateLogDto>> result = new CommonDto<>();
        try {
            result = investorProcessTrackingService.getInvestorOperateLogList(investorId);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 删除操作日志记录
     * @return
     */
    @DeleteMapping("/deleteinvestoroperateLog")
    public CommonDto<String> getInvestorInfo(Integer logId){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = investorProcessTrackingService.deleteInvestorOperateLog(logId);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }


}
