package com.lhjl.tzzs.proxy.service;


import com.lhjl.tzzs.proxy.dto.AdminCreatProjectDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectRatingDto;



public interface ProjectRatingService {
    CommonDto<String> projectRating(ProjectRatingDto body);

    /**
     * 后台管理员审核后，获取事件接受的机构id信息接口
     * @param sourceId 源数据id
     * @param idType id类型，0表示项目提交记录表，1表示项目表信息
     * @return
     */
    CommonDto<AdminCreatProjectDto> adminCreateEvent(Integer sourceId,Integer idType);
}
