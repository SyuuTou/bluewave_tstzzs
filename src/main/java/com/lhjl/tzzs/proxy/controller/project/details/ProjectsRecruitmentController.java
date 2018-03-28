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
import org.springframework.web.bind.annotation.PostMapping;
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
public class ProjectsRecruitmentController extends GenericController{

    @Resource
    private ProjectsService projectsService;
    
    /**
     * 招聘需求信息编辑
     * @param appid
     * @param body 招聘需求请求体
     * @return
     */
    @PutMapping("/v{appid}/saveorupdate/recruitmentrequirement")  
    public CommonDto<Boolean> saveOrUpdateRecruInfo(@PathVariable Integer appid,@RequestBody RecruitmentInfo body){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.saveOrUpdateRecruInfo(appid,body);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 招聘需求信息回显
     * @param appid
     * @param proId
     * @param subjectType 主体类型
     * @return
     */
    @GetMapping("/v{appid}/echo/recruitmentrequirement")
    public CommonDto<RecruitmentInfo> echoRequirementInfo(@PathVariable Integer appid,Integer proId,Integer subjectType){
    	CommonDto<RecruitmentInfo> result =new CommonDto<>();
    	try {
    		result=projectsService.echoRequirementInfo(appid,proId,subjectType);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 招聘信息列表
     * @param appid
     * @param companyId 项目(公司)id  
     * @return
     */
    @GetMapping("/v{appid}/list/recruitmentinfo")
    public CommonDto<List<Recruitment>> listRecruInfo(@PathVariable Integer appid,Integer companyId,Integer subjectType){
    	CommonDto<List<Recruitment>> result =new CommonDto<>();
    	try {
    		result=projectsService.listRecruInfos(appid,companyId,subjectType);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    
    /**
     * 根据id删除招聘职位信息
     * @param appid
     * @param id 招聘职位信息id
     * @return
     */
    @DeleteMapping("/v{appid}/del/recruitmentbyid")
    public CommonDto<Boolean> deleteRecruInfoById(@PathVariable Integer appid,Integer id,Integer subjectType){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.removeRecruInfoById(appid,id,subjectType);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 保存或者更新公司招聘职位信息
     * @param appid
     * @param body 公司的招聘职位信息
     * @return
     */
    @PostMapping("/v{appid}/saveorupdate/recruitment")  
    public CommonDto<Boolean> saveOrUpdateRecruitment(@PathVariable Integer appid,@RequestBody Recruitment body){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.saveOrUpdateRecruitment(appid,body);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 获取岗位类型的元数据
     * @param appid
     * @return
     */
    @GetMapping("/v{appid}/source/jobtype")  
    public CommonDto<List<MetaJobType>> getMetaJobTypes(@PathVariable Integer appid){
    	CommonDto<List<MetaJobType>> result =new CommonDto<>();
    	try {
    		result=projectsService.getMetaJobTypes(appid);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    
    
}