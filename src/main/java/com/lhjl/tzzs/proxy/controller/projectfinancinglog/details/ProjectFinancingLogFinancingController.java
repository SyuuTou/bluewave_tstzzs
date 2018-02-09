package com.lhjl.tzzs.proxy.controller.projectfinancinglog.details;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.projectfinancinglog.ProjectFinancingLogHeadInputDto;
import com.lhjl.tzzs.proxy.dto.projectfinancinglog.ProjectFinancingLogHeadOutputDto;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsProject;
import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.service.ProjectFinancingLogService;

@RestController
public class ProjectFinancingLogFinancingController extends GenericController {
	@Resource
    private ProjectFinancingLogService projectFinancingLogService;
	
	/**
     * 根据投资事件id获取投资事件的详情
     * @param appid
     * @param projectFinancingLogId 投资事件id
     * @return
     */
    @GetMapping("/v{appid}/projectfinancinglog/byid")
    public CommonDto<ProjectFinancingLog> echoProjectFinancingLogById(@PathVariable("appid") Integer appid,Integer projectFinancingLogId){
    	CommonDto<ProjectFinancingLog> result =new CommonDto<>();
    	try {
    		result=projectFinancingLogService.echoProjectFinancingLogById(appid,projectFinancingLogId);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
   
}
