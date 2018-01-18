package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdminLogoOutputDto;
import com.lhjl.tzzs.proxy.service.ProjectAdminService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
