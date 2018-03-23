package com.lhjl.tzzs.proxy.controller.project.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.service.ProjectAdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @param subjectId 主体id
     * @param subjectType 主体的类型，
     * 	1 代表项目
     * 	2 代表机构
     * @return
     */
    @GetMapping("get/project/logo")
    public CommonDto<ProjectAdminLogoOutputDto> getProjectLogo(Integer subjectId, Integer subjectType){
    CommonDto<ProjectAdminLogoOutputDto> result = new CommonDto<>();

    try {
    	result = projectAdminService.getProjectLogoAndMainInfo(subjectId,subjectType);
    }catch (Exception e){
        this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        result.setData(null);
        result.setMessage("服务器端发生错误");
        result.setStatus(502);
    }

    return result;
    }

    /**
     * 保存或者更新项目详情主体(项目/机构...)头部基本信息
     * @param body 项目详情部分头部信息请求体
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
