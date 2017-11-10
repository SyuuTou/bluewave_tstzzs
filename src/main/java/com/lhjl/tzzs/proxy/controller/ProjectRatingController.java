package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectRatingDto;
import com.lhjl.tzzs.proxy.service.ProjectRatingService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class ProjectRatingController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProjectRatingController.class);
    @Resource
    private ProjectRatingService projectRatingService;

    @PostMapping("admin/project/rating")
    public CommonDto<String> projectRatingLevel(@RequestBody ProjectRatingDto body){
        CommonDto<String> result = new CommonDto<>();

        try {
        result = projectRatingService.projectRating(body);
        }catch (Exception e){
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
