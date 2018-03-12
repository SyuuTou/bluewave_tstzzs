package com.lhjl.tzzs.proxy.controller.project.details;

import java.util.List;

import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.service.ProjectsService;

/**
 * 
 * @author IdataVC
 * 项目融资历史接口
 */
@RestController
public class ProjectsFinancingLogController extends GenericController{

    @Resource
    private ProjectsService projectsService;
    
    /**
     * 投资方的智能搜索
     * @param appid
     * @param keyword
     * @return
     */
    @GetMapping("/v{appid}/intelligentsearch/inves")
    public CommonDto<List<InvestmentInstitutions>> intelligentSearch(@PathVariable Integer appid,String keyword){
    	CommonDto<List<InvestmentInstitutions>> result =new CommonDto<>();
    	try {
    		result=projectsService.intelligentSearch(appid,keyword);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**   
	 * 更新融资历史相关的投资机构信息
	 * @param appid
	 * @param body 融资历史单阶段对应的投资机构信息
	 * @return
	 */
    @PutMapping("/v{appid}/editrelativeinvest")
    public CommonDto<Boolean> editRelativeInvestmentInfo(@PathVariable("appid") Integer appid,@RequestBody InvestmentInstitutionsProject body){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.updateRelativeInvestmentInfo(appid,body);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 移除项目的融资历史单阶段对应的投资机构信息
     * @param appid
     * @param projectId  融资历史表中的主键id
     * @param investmentInstitutionsId  投资机构id
     * @return
     */
    @DeleteMapping("/v{appid}/removesingleinvest")  
    public CommonDto<Boolean> removeSingleInvestment(@PathVariable("appid") Integer appid,Integer projectId,Integer investmentInstitutionsId){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.removeSingleInvestment(appid,projectId,investmentInstitutionsId);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		  
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 返回单条融资历史记录的详细信息
     * @param appid
     * @param financingLodId 融资历史记录的id
     * @return
     */
    @GetMapping("/v{appid}/singlefinancinglogDetails")
    public CommonDto<List<InvestmentInstitutionsProject>> financinglogDetails(@PathVariable("appid") Integer appid,Integer financingLogId){
    	CommonDto<List<InvestmentInstitutionsProject>> result =new CommonDto<>();
    	try {
    		result=projectsService.getFinancingLogDetails(appid,financingLogId);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		  
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    
    /**
     * 更新融资历史的单条信息，保存和更新融资历史的相关信息
     * @param appid
     * @param body 融资历史的请求体
     * @return
     */
    @PutMapping("/v{appid}/updatefinancingloginfo")
    public CommonDto<Boolean> updateFinancingLogInfo(@PathVariable("appid") Integer appid,@RequestBody ProjectFinancingLog body){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.updateFinancingLog(appid,body);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    
    /**
     * 获取项目的融资历史信息列表
     * @param appid
     * @param projectId
     * @return
     */
    @GetMapping("/v{appid}/financinglogs")
    public CommonDto<List<ProjectFinancingLog>> getFinancingLogs(@PathVariable("appid") Integer appid,Integer projectId){
    	CommonDto<List<ProjectFinancingLog>> result =new CommonDto<>();
    	try {
    		result=projectsService.getFinancingLogs(appid,projectId);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 删除融资历史表的单条
     * @param appid
     * @param id融资历史表的主键id
     * @return
     */
    @DeleteMapping("/v{appid}/removefinancinglog")
    public CommonDto<Boolean> removeFinancingLogById(@PathVariable("appid") Integer appid,Integer id){
    	CommonDto<Boolean> result=new CommonDto<>();
    	try {
    		result=projectsService.removeFinancingLogById(appid,id);
    	}catch(Exception e) {
    		this.LOGGER.info(e.getMessage(), e.fillInStackTrace());
    		result.setData(false);
    		result.setMessage("failure");
    		result.setStatus(500);
    	}
    	return result;
    }
    
    /**
     * 获取融资历史表中所有的融资状态
     * @param appid
     * @return
     */
    @GetMapping("/v{appid}/list/financingstatus")
    public CommonDto<List<String>> getFinancingStatu(@PathVariable("appid") Integer appid){
    	CommonDto<List<String>> result =new CommonDto<>();
    	try {
    		result=projectsService.getFinancingStatus(appid);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    
}