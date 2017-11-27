package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.AdminCreatProjectDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectRatingDto;
import com.lhjl.tzzs.proxy.service.ProjectRatingService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class ProjectRatingController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProjectRatingController.class);
    @Resource
    private ProjectRatingService projectRatingService;

    //@PostMapping("admin/project/rating")
    @GetMapping("admin/project/rating")
    public CommonDto<String> projectRatingLevel(Integer projectId,Integer ratingStage,String ratingDiscription,String ratingAdminName){
        CommonDto<String> result = new CommonDto<>();

        //POST请求改GET请求时将参数转化一下
        ProjectRatingDto body = new ProjectRatingDto();
        body.setProjectId(projectId);
        body.setRatingAdminName(ratingAdminName);
        body.setRatingDiscription(ratingDiscription);
        body.setRatingStage(ratingStage);


        try {
        result = projectRatingService.projectRating(body);
        }catch (Exception e){
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 后台管理员审核后，获取事件接受的机构id信息接口
     * @param sourceId 源数据id
     * @param idType id类型，0表示项目提交记录表，1表示项目表信息
     * @return
     */
    @GetMapping("admin/creat/event")
    public CommonDto<AdminCreatProjectDto> adminCreateEvent(Integer sourceId,Integer idType){
        CommonDto<AdminCreatProjectDto> result = new CommonDto<>();

        try {
            result = projectRatingService.adminCreateEvent(sourceId,idType);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }

        return result;
    }
}
