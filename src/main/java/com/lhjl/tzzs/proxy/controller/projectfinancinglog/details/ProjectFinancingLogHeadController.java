package com.lhjl.tzzs.proxy.controller.projectfinancinglog.details;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.projectfinancinglog.ProjectFinancingLogHeadOutputDto;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.service.ProjectFinancingLogService;

@RestController
public class ProjectFinancingLogHeadController extends GenericController {
	@Resource
    private ProjectFinancingLogService projectFinancingLogService;
	
	/**
	 * 输出投资事件详情中的头部信息
	 * @param appid
	 * @param projectFinancingLogId
	 * @return
	 */
    @GetMapping("/v{appid}/echo/head/projectfinancinglog")
    public CommonDto<ProjectFinancingLogHeadOutputDto> listInvestorsInfo(@PathVariable Integer appid,Integer projectFinancingLogId){
    	CommonDto<ProjectFinancingLogHeadOutputDto> result =new CommonDto<>();
    	try {
    		result = projectFinancingLogService.echoProjectFinancingLogHead(appid,projectFinancingLogId);
    	}catch(Exception e) {
    		this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);  
    	}
        return result;
    }
    /**
     * 根据关键字获取项目的信息
     * @param appid
     * @param keyword
     * @return
     */
    @GetMapping("/v{appid}/echo/project/byshortname")
    public CommonDto<List<Projects>> echoProjectByShortName(@PathVariable Integer appid,String keyword){
    	CommonDto<List<Projects>> result =new CommonDto<>();
    	try {
    		result = projectFinancingLogService.blurScanProjectByShortName(appid,keyword);
    	}catch(Exception e) {
    		this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);  
    	}
        return result;
    }
    
   
}
