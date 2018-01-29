package com.lhjl.tzzs.proxy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
 * @author lmy
 *
 */
@RestController
public class ProjectsController extends GenericController{

    private static final Logger log = LoggerFactory.getLogger(ProjectsController.class);

    @Resource
    private ProjectsService projectsService;
    @Resource
    private UserLoginService userLoginService;
    @Resource
    private UserInfoService userInfoService;
    
    @Value("${pageNum}")  
    private String defaultPageNum;

    @Value("${pageSize}")  
    private String defaultPageSize;
    /**
     * 根据appid以及token获取用户信息
     * @param appid
     * @param userId
     * @return
     */
    @GetMapping("/v{appid}/echo/user/byappidandtoken")
    public CommonDto<Users> getUserById(@PathVariable Integer appid,String token){
    	CommonDto<Users> result =new CommonDto<>();
    	try {
    		Integer userId = userLoginService.getUserIdByToken(token,appid);
			result=userInfoService.getUserByUserId(userId);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
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
     * 根据id删除进展的信息
     * @param appid
     * @param id 进展的id
     * @return
     */
    @DeleteMapping("/v{appid}/del/progressinfobyid")
    public CommonDto<Boolean> deleteProgressInfoById(@PathVariable Integer appid,Integer id){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.removeProgressInfoById(appid,id);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 增加或者更新公司进展的消息
     * @param appid
     * @param body
     * @return
     */
    @PostMapping("/v{appid}/saveorupdate/progress")
    public CommonDto<Boolean> saveOrUpdateProgressInfo(@PathVariable("appid") Integer appid,@RequestBody ProjectProgress body){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.saveOrUpdateProgressInfo(appid,body);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 公司进展列表
     * @param appid
     * @param companyId 公司id
     * @return
     */
    @GetMapping("/v{appid}/list/project/progress")
    public CommonDto<List<ProjectProgress>> listProProgress(@PathVariable("appid") Integer appid,Integer companyId){
    	CommonDto<List<ProjectProgress>> result =new CommonDto<>();
    	try {
    		result=projectsService.listProProgress(appid,companyId);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
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
     * @param proId 项目id
     * @return
     */
    @GetMapping("/v{appid}/echo/recruitmentrequirement")
    public CommonDto<RecruitmentInfo> echoRecruInfo(@PathVariable Integer appid,Integer proId){
    	CommonDto<RecruitmentInfo> result =new CommonDto<>();
    	try {
    		result=projectsService.echoRequirementInfo(appid,proId);
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
    public CommonDto<Boolean> deleteRecruInfoById(@PathVariable Integer appid,Integer id){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.removeRecruInfoById(appid,id);
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
    /**
     * 根据id回显 公司全部信息(主要用于获取项目简介 以及 投资亮点)
     * @param appid
     * @param proId 项目id
     * @return
     */
    @GetMapping("/v{appid}/echo/proinfobyid")
    public CommonDto<Projects> echoProInfoById(@PathVariable Integer appid,Integer proId){
    	CommonDto<Projects> result =new CommonDto<>();
    	try {
    		result=projectsService.getProInfoById(appid,proId);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		  
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    
    /**
     * 根据id更新公司相关信息(主要用于更新项目简介 以及 投资亮点)
     * @param appid
     * @param proId  
     * @return
     */
    @PutMapping("/v{appid}/edit/proinfobyid")
    public CommonDto<Boolean> editProInfoById(@PathVariable Integer appid,@RequestBody Projects body){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.updateProInfos(appid,body);
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
    @PutMapping("/v{appid}/editrelativeincest")
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
     * @param id  investment_institutions_project表中的主键id
     * @return
     */
    @DeleteMapping("/v{appid}/removesingleinvest")
    public CommonDto<Boolean> removeSingleInvestment(@PathVariable("appid") Integer appid,Integer id){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.removeSingleInvestment(appid,id);
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
    public CommonDto<List<InvestmentInstitutionsProject>> financinglogDetails(@PathVariable("appid") Integer appid,Integer financingLodId){
    	CommonDto<List<InvestmentInstitutionsProject>> result =new CommonDto<>();
    	try {
    		result=projectsService.getFinancingLogDetails(appid,financingLodId);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		  
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    
    /**
     * 保存和更新融资历史的相关信息
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
    /**
     * 天使投资指数的项目列表
     * @param appid
     * @param body
     * @return
     */
    @PostMapping("/v{appid}/list/projects")
    public CommonDto<Map<String, Object>> listProject(@PathVariable("appid") Integer appid,@RequestBody ProjectsListInputDto body){
    	CommonDto<Map<String, Object>> result=new CommonDto<>();
    	try {
    		result=projectsService.listProInfos(appid,body);
    	}catch(Exception e) {
    		this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
    	return result;
    }
    /**
     * 更新项目的跟进状态
     * @param appid
     * @param body 项目的id以及项目的更新状态
     * @return
     */
    @PutMapping("/v{appid}/update/projects/status")
    public CommonDto<Boolean> updateFollowStatus(@PathVariable("appid") Integer appid,@RequestBody ProjectsUpdateInputDto body){
    	CommonDto<Boolean> result = new CommonDto<>();
    	try {
    		result=projectsService.updateFollowStatus(appid,body);
    	}catch(Exception e) {
    		this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
    	return result;
    }

    /**
     * 项目跟进状态回显
     * @param projectId
     * @param appid
     * @return
     */
    @GetMapping("/v{appid}/get/projects/status")
    public CommonDto<ProjectFollowStatus> getProjectsStatus(Integer projectId, @PathVariable Integer appid){
        CommonDto<ProjectFollowStatus> result = new CommonDto<>();

        try {
            result = projectsService.getFollowStatus(projectId, appid);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());

            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }

        return result;
    }
    /**
     * 读取项目跟进状态元数据
     * @return
     */
    @GetMapping("/v{appid}/source/status")
    public CommonDto<List<MetaFollowStatus>> getStatusSource(@PathVariable Integer appid ){
    	CommonDto<List<MetaFollowStatus>> result =new CommonDto<>();
    	try {
    		result=projectsService.getFollowStatusSource(appid);
    	}catch(Exception e) {
    		this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
    	return result;
    	
    }
    /**
     * 读取项目来源元数据
     * @return
     */
    @GetMapping("/v{appid}/source/projects")
    public CommonDto<List<MetaDataSourceType>> getProjectsSource(@PathVariable Integer appid ){
    	CommonDto<List<MetaDataSourceType>> result =new CommonDto<>();
    	try {
    		result=projectsService.getProjectsSource(appid);
    	}catch(Exception e) {
    		this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
    	return result;
    	
    }
    /**
     * 获取项目等级的元数据
     * @param appid
     * @return
     */
 /*   @GetMapping("/v{appid}/ratingstage/projects")
    public CommonDto<List<AdminProjectRatingLog>> getProjectsRatingStage(@PathVariable Integer appid ){
    	CommonDto<List<AdminProjectRatingLog>> result =new CommonDto<>();
    	try {
    		result=projectsService.getProjectsRatingStages(appid);
    	}catch(Exception e) {
    		this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
    	return result;
    	
    }*/
    /**
     * 查询我关注项目
     * @return
     */
    @ApiOperation(value = "查询我关注的项目列表", notes = "根据用户ID标注请求的唯一")
    @PostMapping("search/myfollow")
    public CommonDto<List<Map<String, Object>>> findProjectByUserId(@RequestBody MyFollowDto body){


        CommonDto<List<Map<String, Object>>> result =new CommonDto<List<Map<String, Object>>>();
        //Users user = (Users) request.getSession().getAttribute("loging_user");
        Integer pageNum = body.getPageNum();
        Integer pageSize = body.getPageSize();
        try {
            //初始化分页信息
            if(pageNum == null){
                pageNum = Integer.parseInt(defaultPageNum);
            }
            if(pageSize == null){
                pageSize = Integer.parseInt(defaultPageSize);
            }
            result = projectsService.findProjectByUserId(body.getUserId(), pageNum, pageSize);
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("项目显示页面异常，请稍后再试");
            log.error(e.getMessage(),e);
        }
        return result;
    }
    /**
     * 搜索结果集显示三条记录
     * @return
     */
    @ApiOperation(value = "搜索结果记录", notes = "根据用户的id与项目的名字来标识请求的唯一性")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body", dataType = "String", name = "userId", value = "用户ID", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "shortName", value = "项目的名字", required = true)
    })
    @PostMapping("search/Mprojects")
    public CommonDto<List<Map<String, Object>>> findProjectByShortName(@RequestBody ShuruDto body){
        CommonDto<List<Map<String,Object>>> result =new CommonDto<List<Map<String, Object>>>();
        String shortName=body.getShortName();
        String userId =body.getUserId();
        try {
            result = projectsService.findProjectByShortName(shortName,userId);
        } catch (Exception e) {
            result.setStatus(5102);
            result.setMessage("页面显示异常，请稍后再试");
            log.error(e.getMessage());
        }


        return result;

    }
    /**
     * 搜索结果项目/机构的更多

     * @return
     */
    @ApiOperation(value = "搜索醒目列表的接口", notes = "根据用户的id与项目的名字来标识请求的唯一性")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body", dataType = "String", name = "userId", value = "用户ID", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "shortName", value = "项目的名字", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "pageNum", value = "初始页数", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "pageSize", value = "每页数量", required = true)
    })
    @PostMapping("search/pandi")
    public CommonDto<Map<String,List<Map<String, Object>>>> findProjectAndInvestmentByShortNameAll(@RequestBody ShuruDto body){
        CommonDto <Map<String,List<Map<String, Object>>>> result =new CommonDto <Map<String,List<Map<String, Object>>>>();
        String shortName =body.getShortName();
        String userId =body.getUserId();
        String size ="0";
        String from ="20";
        if(body.getPageNum() != null ){
            size=body.getPageNum();

        }
        if(body.getPageSize() != null){
            from=body.getPageSize();
        }

        try {
            result = projectsService.findProjectByShortNameAll(shortName,userId,size,from);
        } catch (Exception e) {
            result.setStatus(5103);
            result.setMessage("搜索异常，请稍后再试");
            log.error(e.getMessage());
        }
        return result;
    }
    /**
     * 筛选功能
     * @return
     */
    @ApiOperation(value = "筛选结果页面", notes = "根据用户的id、机构类型、领域、城市、轮次、教育背景、工作背景来标注请求的唯一")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body", dataType = "String", name = "userId", value = "用户ID", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "investment_institutions_type", value = "机构类型", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "segmentation", value = "行业领域", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "stage", value = "轮次", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "city", value = "城市", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "educational_background_desc", value = "教育背景", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "working_background_desc", value = "工作背景", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "pageNum", value = "初始页数", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "pageSize", value = "每页数量", required = true)
    })
    @PostMapping("choose/sview")
    public CommonDto<List<Map<String,Object>>>findProjectBySview(@RequestBody SereachDto body)
    {
        CommonDto<List<Map<String,Object>>> result =new CommonDto<>();
        try {
            result = projectsService.findProjectBySview(body);

        } catch (Exception e) {
            result.setStatus(5104);
            result.setMessage("页面显示异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
    /**
     * 图表筛选功能
     * @return
     */
    @ApiOperation(value = "筛选结果页面", notes = "根据用户的id、机构类型、领域、城市、轮次、教育背景、工作背景来标注请求的唯一")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body", dataType = "String", name = "userId", value = "用户ID", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "investment_institutions_type", value = "机构类型", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "segmentation", value = "行业领域", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "stage", value = "轮次", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "city", value = "城市", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "educational_background_desc", value = "教育背景", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "working_background_desc", value = "工作背景", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "pageNum", value = "初始页数", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "pageSize", value = "每页数量", required = true)
    })
    @PostMapping("choose/sviewa")
    public CommonDto<List<Map<String,Object>>>findProjectBySviewA(@RequestBody SereachDto body)
    {
        CommonDto<List<Map<String,Object>>> result =new CommonDto<List<Map<String, Object>>>();
        try {
            result = projectsService.findProjectBySviewA(body);

        } catch (Exception e) {
            result.setStatus(5104);
            result.setMessage("页面显示异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }


    /**
     * 获取项目详情接口
     * @param body
     * @return
     */
    @PostMapping("get/project/details")
    public CommonDto<ProjectDetailOutputDto> getProjectDetails(@RequestBody Map<String,Object> body){
        CommonDto<ProjectDetailOutputDto> result  =new CommonDto<>();
        try {
            result = projectsService.getProjectDetails(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 获取项目当前融资信息
     * @param body
     * @return
     */

    @PostMapping("get/project/financing/now")
    public CommonDto<Map<String,Object>> getProgectFinancingNow(@RequestBody Map<String,Object> body){
        CommonDto<Map<String,Object>> result  = new CommonDto<>();
        try {
            result = projectsService.getProjectFinancingNow(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }

    /**
     * 获取项目的历史融资信息
     * @param body
     * @return
     */
    @PostMapping("get/project/financing/history")
    public CommonDto<List<Map<String,Object>>> getProgectFinancingHistory(@RequestBody Map<String,Object> body){
        CommonDto<List<Map<String,Object>>> result  = new CommonDto<>();
        try {
            result = projectsService.getProjectFinancingHistory(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }

    /**
     * 获取项目团队成员接口
     * @param body
     * @return
     */
    @PostMapping("get/project/financing/team")
    public CommonDto<List<Map<String,Object>>> getProgectFinancingTeam(@RequestBody Map<String,Object> body){
        CommonDto<List<Map<String,Object>>> result  = new CommonDto<>();
        try {
            result = projectsService.getProjectFinancingTeam(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }


    /**
     * 获取项目竞品列表接口
     * @param body
     * @return
     */
    @PostMapping("get/project/competing")
    public CommonDto<List<Map<String,Object>>> getProgectCompeting(@RequestBody Map<String,Object> body){
        CommonDto<List<Map<String,Object>>> result  = new CommonDto<>();
        try {

        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }


    /**
     * 判断项目是否是我的 的接口
     * @param xmid 项目id
     * @param token 用户token
     * @return
     */
    @GetMapping("get/project/ismine")
    public CommonDto<Boolean> judgeProjectIsMine(Integer xmid,String token){
        CommonDto<Boolean> result = new CommonDto<>();
        try {
            result = projectsService.judgeProjectIsMine(xmid,token);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(false);
        }

        return result;
    }

    /**
     * 获取项目管理员列表接口
     * @param body
     * @return
     */
    @PostMapping("project/administrator/list")
    public CommonDto<List<ProjectAdministratorOutputDto>> getProjectAdministratorList(@RequestBody Map<String,Integer> body){
        CommonDto<List<ProjectAdministratorOutputDto>> result = new CommonDto<>();
        List<ProjectAdministratorOutputDto> list = new ArrayList<>();

        try {
            result = projectsService.getProjectAdministractorList(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(list);
        }

        return result;
    }

    /**
     * 通过项目id获取项目信息接口
     * @param body
     * @return
     */
    @PostMapping("project/complexinfo")
    public CommonDto<ProjectComplexOutputDto> getProjectComplexInfo(@RequestBody Map<String,Integer> body){
        CommonDto<ProjectComplexOutputDto> result =new CommonDto<>();

        try {
            result = projectsService.getProjectComplexInfo(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }
}