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
import com.lhjl.tzzs.proxy.service.ProjectFinancingLogService;

@RestController
public class ProjectFinancingLogDetailsController extends GenericController {
	 @Resource
	    private ProjectFinancingLogService projectFinancingLogService;
	
	
//    @PostMapping("/v{appid}/list/investors")
//    public CommonDto<> listInvestorsInfo(@PathVariable Integer appid,Integer projectFinancingLogId){
//    	CommonDto<> result =new CommonDto<>();
//    	try {
//    		result = projectFinancingLogService.listInvestorsInfos(appid,body);
//    	}catch(Exception e) {
//    		this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
//    		
//    		result.setData(null);
//    		result.setMessage("fail");
//    		result.setStatus(500);
//    	}
//        return result;
//    }
   
}
