package com.lhjl.tzzs.proxy.controller.project.details;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.model.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.service.ProjectsService;
import com.lhjl.tzzs.proxy.service.UserInfoService;
import com.lhjl.tzzs.proxy.service.bluewave.UserLoginService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @author IdataVC
 * 项目分布信息
 */
@RestController
public class ProjectsPartsController extends GenericController{

    @Resource
    private ProjectsService projectsService;
    /**
     * 保存或者更新公司(投资机构)地址分部的信息
     * @param appid
     * @param body
     * @return
     */
    @PostMapping("/v{appid}/saveorupdate/part")  
    public CommonDto<Boolean> saveOrUpdatePart(@PathVariable Integer appid,@RequestBody InvestmentInstitutionsAddressPart body){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.saveOrUpdayePart(appid,body);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 根据id删除分部信息
     * @param appid
     * @param partId 分部id
     * @return
     */
    @DeleteMapping("/v{appid}/delete/part")
    public CommonDto<Boolean> deletePartInfoById(@PathVariable Integer appid,Integer id){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.removePartInfoById(appid,id);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 项目(投资机构/公司)分部的列表信息
     * @param appid 扩展字段
     * @param companyType 项目的类别(根据不同的项目类别来列举不同项目的分部信息：1代表项目【产业公司】;2代表机构)
     * @param companyId 项目或者投资机构等的id
     * @return
     */
    @GetMapping("/v{appid}/list/proparts")
    public CommonDto<List<InvestmentInstitutionsAddressPart>> listProPart(@PathVariable("appid") Integer appid,Integer companyType,Integer companyId){
    	CommonDto<List<InvestmentInstitutionsAddressPart>> result =new CommonDto<>();
    	try {
    		result=projectsService.listProPartsByCompanyIdAndProtype(appid,companyType,companyId);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    
}