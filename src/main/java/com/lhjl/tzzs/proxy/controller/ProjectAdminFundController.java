package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.FundDto.FundInputDto;
import com.lhjl.tzzs.proxy.dto.FundDto.FundOutputDto;
import com.lhjl.tzzs.proxy.service.ProjectAdminFundService;
import com.lhjl.tzzs.proxy.service.ProjectAdminTeamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@RestController
public class ProjectAdminFundController extends GenericController{

    @Resource
    private ProjectAdminFundService projectAdminFundService;

    /**
     * 获取项目基金列表
     * @param projectId
     * @return
     */
    @GetMapping("/getfundList")
    public CommonDto<List<FundOutputDto>> getFundList(Integer projectId){

        CommonDto<List<FundOutputDto>> result = new CommonDto<>();
        try {
            result = projectAdminFundService.getFundList(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 增加或更新基金
     * @param body
     * @return
     */
    @PostMapping("/addOrUpdatefund")
    public CommonDto<String> addOrUpdateFund(@RequestBody FundInputDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = projectAdminFundService.addOrUpdateFund(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 删除基金
     * @param fundId
     * @return
     */
    @GetMapping("/deletefund")
    public CommonDto<String> deleteFund(Integer fundId){

        CommonDto<String> result = new CommonDto<>();
        try {
            result = projectAdminFundService.deleteFund(fundId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

}
