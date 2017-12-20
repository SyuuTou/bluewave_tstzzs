package com.lhjl.tzzs.proxy.controller;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectReqDto;
import com.lhjl.tzzs.proxy.dto.ProjectResDto;
import com.lhjl.tzzs.proxy.service.ProjectSearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FilterController extends GenericController {


    @Resource(name = "projectSearchService")
    private ProjectSearchService projectSearchService;

    @PostMapping("project/search")
    public CommonDto<List<ProjectResDto>> search(@RequestBody ProjectReqDto reqDto){
        CommonDto<List<ProjectResDto>> result = null;

        result = projectSearchService.projectFilter(reqDto);

        return result;
    }
}
