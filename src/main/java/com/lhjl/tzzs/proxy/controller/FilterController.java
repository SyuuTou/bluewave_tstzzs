package com.lhjl.tzzs.proxy.controller;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.ProjectReqDto;
import com.lhjl.tzzs.proxy.dto.ProjectResDto;
import com.lhjl.tzzs.proxy.service.ProjectSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class FilterController extends GenericController {


    @Resource(name = "projectSearchService")
    private ProjectSearchService projectSearchService;

    @PostMapping("project/search")
    public CommonDto<List<ProjectResDto>> search(@RequestBody ProjectReqDto reqDto){
        CommonDto<List<ProjectResDto>> result = null;

        try {
            result = projectSearchService.projectFilter(reqDto);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());

            result= new CommonDto<>();
            result.setStatus(500);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }

    @PostMapping("project/search/financingtime")
    public CommonDto<List<ProjectResDto>> searchRecently(@RequestBody ProjectReqDto reqDto){
        CommonDto<List<ProjectResDto>> result  = new CommonDto<>();
        try {
            result = projectSearchService.projectFilterOrderByFinancing(reqDto);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }

    @PostMapping("project/related/institution")
    public CommonDto<List<Map<String,Object>>> getRelatedInstitution(@RequestBody ProjectReqDto reqDto){
        CommonDto<List<Map<String,Object>>> result = new CommonDto<>();
        try {
            result = projectSearchService.ralatedInstitution(reqDto);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }

    @PostMapping("project/search/statistics")
    public CommonDto<List<HistogramList>> projectSearchStatistics(@RequestBody ProjectReqDto reqDto){
        CommonDto<List<HistogramList>> result = null;

        result = projectSearchService.projectFilterStatistices(reqDto);

        return result;
    }

    /**
     * 获取精选项目信息
     * @param token
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("project/high/quality")
    public CommonDto<List<ProjectResDto>> getHighQualityProject(String token,Integer pageNum,Integer pageSize){
        CommonDto<List<ProjectResDto>> result  = new CommonDto<>();

        try {
            result = projectSearchService.projectHightQuality(token, pageNum, pageSize);

        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }

}
