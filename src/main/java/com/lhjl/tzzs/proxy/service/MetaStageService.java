package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.MetaProjectStage;

import java.util.List;

public interface MetaStageService {
    /**
     * 获取阶段元数据接口
     * @return
     */
    CommonDto<List<MetaProjectStage>> getMetaProjectStage();
}
