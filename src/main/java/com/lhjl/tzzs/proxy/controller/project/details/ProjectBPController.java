package com.lhjl.tzzs.proxy.controller.project.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.ProjectBusinessPlanImage;
import com.lhjl.tzzs.proxy.service.ProjectBusinessPlanService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ProjectBPController extends GenericController {

    @Resource
    private ProjectBusinessPlanService projectBusinessPlanService;

    /**
     * 商业计划书解析
     * @param file
     * @param projectId
     * @param token
     * @return
     */
    @PostMapping("resolve/{projectId}/business/plan")
    public CommonDto<List<ProjectBusinessPlanImage>> handlerProjectBusinessPlan(@RequestParam("file") MultipartFile file, @PathVariable Integer projectId, String token){

        CommonDto<List<ProjectBusinessPlanImage>> result = null;

        try {
            result = projectBusinessPlanService.resolveProjectBusinessPlan(file, projectId, token);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }


    /**
     * 更新图片
     * @param reqDto
     * @param projectId
     * @return
     */
    @PutMapping("resolve/{projectId}/business/plan")
    public CommonDto<String> updateProjectBusinessPlanImage(@RequestBody List<ProjectBusinessPlanImage> reqDto, @PathVariable Integer projectId){

        try {
            return projectBusinessPlanService.updateProjectBusinessPlanImage(reqDto,projectId);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        return new CommonDto<>("error","error",500);
    }

}
