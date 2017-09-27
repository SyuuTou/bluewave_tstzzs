package com.lhjl.tzzs.proxy.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentDataDto;
import com.lhjl.tzzs.proxy.dto.SereachDto;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.ProjectsService;
/**
 *
 * @author lmy
 *
 */
@RestController
public class ProjectsController {

    private static final Logger log = LoggerFactory.getLogger(FinancingController.class);

    @Resource
    private ProjectsService projectsService;
    /**
     * 查询我关注项目
     * @param userId 用户id
     * @return
     */

    @GetMapping("search/myfollow/{userId}")
    public CommonDto<List<Projects>> findProjectByUserId(@PathVariable String userId ){


        CommonDto<List<Projects>> result =new CommonDto<List<Projects>>();
        //Users user = (Users) request.getSession().getAttribute("loging_user");

        try {
            result = projectsService.findProjectByUserId(userId);
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("项目显示页面异常，请稍后再试");
            log.error(e.getMessage());
        }
        return result;
    }
    /**
     * 搜索结果集显示三条记录
     * @param shortName 项目简称
     * @param userId 用户id
     * @return
     */
    @GetMapping("search/Mprojects/{shortName}/{userId}")
    public CommonDto<List<Map<String, Object>>> findProjectByShortName(@PathVariable String shortName,@PathVariable String userId){
        CommonDto<List<Map<String,Object>>> result =new CommonDto<List<Map<String, Object>>>();
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
     * @param shortName 简称
     * @param userId 用户id
     * @return
     */
    @GetMapping("search/pandi/{shortName}/{userId}")
    public CommonDto<Map<String,List<Map<String, Object>>>> findProjectAndInvestmentByShortNameAll(@PathVariable String shortName,@PathVariable String userId){
        CommonDto <Map<String,List<Map<String, Object>>>> result =new CommonDto <Map<String,List<Map<String, Object>>>>();
        try {
            result = projectsService.findProjectByShortNameAll(shortName,userId);
        } catch (Exception e) {
            result.setStatus(5103);
            result.setMessage("搜索异常，请稍后再试");
            log.error(e.getMessage());
        }
        return result;
    }
    /**
     * 筛选功能
     * @param investmentType 机构类型
     * @param segmentation 领域
     * @param stage  融资阶段
     * @param city   城市
     * @param userId   用户id
     * @param working_background_desc  工作背景
     * @param educational_background_desc  教育背景
     * @return
     */
    @PostMapping("choose/sview")
    public CommonDto<List<Map<String,Object>>>findProjectBySview(@RequestBody SereachDto body)
    {
        CommonDto<List<Map<String,Object>>> result =new CommonDto<List<Map<String, Object>>>();
        try {
            result = projectsService.findProjectBySview(body);

        } catch (Exception e) {
            result.setStatus(5104);
            result.setMessage("页面显示异常，请稍后再试");
            log.error(e.getMessage());
        }
        return result;
    }
}