package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.MetaProjectStageMapper;
import com.lhjl.tzzs.proxy.model.MetaProjectStage;
import com.lhjl.tzzs.proxy.service.MetaStageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MetaStageServiceImpl  implements MetaStageService{

    @Resource
    private MetaProjectStageMapper metaProjectStageMapper;

    @Override
    public CommonDto<List<MetaProjectStage>> getMetaProjectStage() {
        CommonDto<List<MetaProjectStage>> result = new CommonDto<>();

        List<MetaProjectStage> list =  metaProjectStageMapper.findAll();

        result.setMessage("success");
        result.setStatus(200);
        result.setData(list);

        return result;
    }
}
