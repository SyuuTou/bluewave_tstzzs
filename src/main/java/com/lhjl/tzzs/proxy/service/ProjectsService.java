package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.Projects;

/**
 * 项目
 * @author PP
 *
 */
public interface ProjectsService {

    /**
     * 用户关注的项目
     * @param userId
     * @return
     */
    CommonDto<List<Projects>> findProjectByUserId(String userId);
    CommonDto<List<Map<String, Object>>> findProjectByShortName(String shortName,String userId);
    CommonDto<Map<String,List<Map<String, Object>>>> findProjectByShortNameAll(String shortName,String userId);
    CommonDto< List<Map<String, Object>>>findProjectBySview( Integer type, String segmentation,
                                                             String stage, String city,String userId,
                                                             String working_background_desc, String educational_background_desc
    );

}