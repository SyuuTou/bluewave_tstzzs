package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.MetaProjectStage;
import com.lhjl.tzzs.proxy.service.MetaStageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MetaStageController extends GenericController{

    @Resource
    private MetaStageService metaStageService;

    @GetMapping("get/meta/project/stage")
    public CommonDto<List<MetaProjectStage>> getMetaProjectStage(){
        CommonDto<List<MetaProjectStage>> result = new CommonDto<>();

        try {
            result = metaStageService.getMetaProjectStage();
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }
}
