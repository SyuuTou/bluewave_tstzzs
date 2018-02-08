package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectFinancingLogInputDto;
import com.lhjl.tzzs.proxy.dto.projectfinancinglog.ProjectFinancingLogHeadOutputDto;
import com.lhjl.tzzs.proxy.model.Projects;

import java.util.List;
import java.util.Map;

public interface ProjectFinancingLogService {

    /**
     * 后台读取项目融资历史列表的接口
     * @param body
     * @return
     */
    CommonDto<Map<String,Object>> getProjectFinancingLogList(ProjectFinancingLogInputDto body);
    /**
	 * 输出投资事件详情中的头部信息
	 * @param appid
	 * @param projectFinancingLogId
	 * @return
	 */
	CommonDto<ProjectFinancingLogHeadOutputDto> echoProjectFinancingLogHead(Integer appid,
			Integer projectFinancingLogId);
	/**
     * 根据关键字获取项目的信息
     * @param appid
     * @param keyword
     * @return
     */
	CommonDto<List<Projects>> blurScanProjectByShortName(Integer appid, String keyword);
    
}
