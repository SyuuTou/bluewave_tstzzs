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

import com.lhjl.tzzs.proxy.model.MetaDataSourceType;
import com.lhjl.tzzs.proxy.model.MetaFollowStatus;
import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.service.ProjectsService;

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

    @Value("${pageNum}")
    private String defaultPageNum;

    @Value("${pageSize}")
    private String defaultPageSize;
    
    /**
     * 返回单条融资历史记录的详细信息
     * @param appid
     * @param financingLodId 融资历史记录的id
     * @return
     */
    @GetMapping("/v{appid}/singlefinancinglogDetail")
    public CommonDto<Boolean> financinglogDetails(@PathVariable Integer appid,Integer financingLodId){
    	CommonDto<Boolean> result =new CommonDto<>();
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
    public CommonDto<Boolean> updateFinancingLogInfo(@PathVariable Integer appid,@RequestBody ProjectFinancingLog body){
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
     * 获取项目的融资历史信息
     * @param appid
     * @param projectId
     * @return
     */
    @GetMapping("/v{appid}/financinglogs")
    public CommonDto<List<ProjectFinancingLog>> getFinancingLogs(@PathVariable Integer appid,Integer projectId){
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
     * @param financiingLogId
     * @return
     */
    @PutMapping("/v{appid}/removefinancinglog")
    public CommonDto<Boolean> removeFinancingLogById(@PathVariable Integer appid,@RequestBody FinancingLogDelInputDto body){
    	System.err.println(body+"******");
    	CommonDto<Boolean> result=new CommonDto<>();
    	try {
    		result=projectsService.removeFinancingLogById(appid,body);
    	}catch(Exception e) {
    		this.LOGGER.info(e.getMessage(), e.fillInStackTrace());
    		result.setData(false);
    		result.setMessage("failure");
    		result.setStatus(500);
    	}
    	return result;
    }
    /**
     * 获取融资状态的所有数据
     * @param appid
     * @return
     */
    @GetMapping("/v{appid}/list/financingstatus")
    public CommonDto<List<String>> getFinancingStatu(@PathVariable Integer appid){
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