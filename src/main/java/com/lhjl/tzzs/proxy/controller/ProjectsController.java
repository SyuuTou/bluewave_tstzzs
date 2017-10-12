package com.lhjl.tzzs.proxy.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.lhjl.tzzs.proxy.dto.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.model.Users;
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
public class ProjectsController {

    private static final Logger log = LoggerFactory.getLogger(ProjectsController.class);

    @Resource
    private ProjectsService projectsService;

    @Value("${pageNum}")
    private String defaultPageNum;

    @Value("${pageSize}")
    private String defaultPageSize;

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
            log.error(e.getMessage());
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
        String from ="10";
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
        CommonDto<List<Map<String,Object>>> result =new CommonDto<List<Map<String, Object>>>();
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
}