package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.service.ProjectAdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProjectAdminController extends GenericController{

    @Resource
    private ProjectAdminService projectAdminService;

    /**
     * 获取后台项目编辑页的
     * @param projectId
     * @param projectType
     * @return
     */
    @GetMapping("get/project/logo")
    public CommonDto<ProjectAdminLogoOutputDto> getProjectLogo(Integer projectId, Integer projectType){
    CommonDto<ProjectAdminLogoOutputDto> result = new CommonDto<>();

    try {
    result = projectAdminService.getProjectLogoAndMainInfo(projectId,projectType);
    }catch (Exception e){
        this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        result.setData(null);
        result.setMessage("服务器端发生错误");
        result.setStatus(502);
    }

    return result;
    }

    /**
     * 更改项目logo和其他基本信息的接口
     * @param body
     * @return
     */
    @PostMapping("update/project/logo")
    public CommonDto<String> updateProjectLogo(@RequestBody ProjectAdminLogoInputDto body){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = projectAdminService.updateProjectLogoAndMainInfo(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }

    /**
     * 获取项目基本信息的接口
     * @param projectId
     * @param projectType
     * @return
     */
    @GetMapping("get/project/baseinfo")
    public CommonDto<ProjectAdminBaseInfoDto> getProjectBaseInfo(Integer projectId, Integer projectType){
        CommonDto<ProjectAdminBaseInfoDto> result = new CommonDto<>();

        try {
            result = projectAdminService.getProjectBaseInfo(projectId,projectType);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }

    /**
     * 更新项目信息的接口
     * @return
     */
    @PostMapping("update/project/baseinfo")
    public CommonDto<String> updateProjectBaseInfo(@RequestBody ProjectAdminBaseInfoInputDto body){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = projectAdminService.updateProjectBaseInfo(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }
}
