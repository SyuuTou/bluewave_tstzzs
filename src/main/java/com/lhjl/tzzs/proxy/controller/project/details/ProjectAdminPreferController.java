package com.lhjl.tzzs.proxy.controller.project.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectPreferDto;
import com.lhjl.tzzs.proxy.service.ProjectAdminPreferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author caochuangui
 * @date 2018-1-24 14:57:59
 */
@RestController
public class ProjectAdminPreferController extends GenericController{

    @Resource
    private ProjectAdminPreferService projectPreferService;

    /**
     * 获取投资偏好和理念
     * @return
     */
    @GetMapping("/getprojectprefer")
    public CommonDto<ProjectPreferDto> getProjectPrefer(Integer projectId,Integer subjectType){
        CommonDto<ProjectPreferDto> result = new CommonDto<>();
        try {
            result = projectPreferService.getProjectprefer(projectId,subjectType);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }
        return result;
    }

    /**
     * 添加或更新投资偏好和理念
     * @param body
     * @return
     */
    @PostMapping("/addOrUpdatePreferLog")
    public CommonDto<String> addOrUpdatePreferLog(@RequestBody ProjectPreferDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = projectPreferService.addOrUpdatePrefer(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }
        return result;
    }

}
