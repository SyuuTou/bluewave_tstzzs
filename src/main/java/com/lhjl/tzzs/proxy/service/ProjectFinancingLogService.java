package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectFinancingLogInputDto;

import java.util.Map;

public interface ProjectFinancingLogService {

    /**
     * 后台读取项目融资历史列表的接口
     * @param body
     * @return
     */
    CommonDto<Map<String,Object>> getProjectFinancingLogList(ProjectFinancingLogInputDto body);
    
}
