package com.lhjl.tzzs.proxy.service;


import com.lhjl.tzzs.proxy.dto.AdminCreatProjectDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectRatingDto;
import com.lhjl.tzzs.proxy.model.AdminProjectRatingLog;

import java.util.Map;


public interface ProjectRatingService {
    CommonDto<String> projectRating(ProjectRatingDto body);

    /**
     * 后台管理员审核后，获取事件接受的机构id信息接口
     * @param sourceId 源数据id
     * @param idType id类型，0表示项目提交记录表，1表示项目表信息
     * @return
     */
    CommonDto<AdminCreatProjectDto> adminCreateEvent(Integer sourceId,Integer idType);

    /**
     * 读取项目评级信息的接口
     * @param projectId
     * @return
     */
    CommonDto<AdminProjectRatingLog> getProjectRatingInfo(Integer projectId);

    /**
     * 获取评级项目的,提交方,还有选择机构ids数组的接口
     * @param projectId
     * @return
     */
    CommonDto<AdminCreatProjectDto> getUserAndInvestmentInstitutionIds(Integer projectId);
}
