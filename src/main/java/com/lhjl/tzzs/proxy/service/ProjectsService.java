package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.ProjectDetailOutputDto;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.SereachDto;
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
    CommonDto<List<Map<String, Object>>> findProjectByUserId(String userId, Integer pageNum, Integer pageSize);
    CommonDto<List<Map<String, Object>>> findProjectByShortName(String shortName,String userId);
    CommonDto<Map<String,List<Map<String, Object>>>> findProjectByShortNameAll(String shortName,String userId,String size,String from);
    CommonDto< List<Map<String, Object>>>findProjectBySview(SereachDto sereachDto);
    CommonDto< List<Map<String, Object>>>findProjectBySviewA(SereachDto sereachDto);

    /**
     * 获取项目详细信息接口
     * @param body
     * @return
     */
    CommonDto<ProjectDetailOutputDto> getProjectDetails(Map<String,Object> body);

    /**
     * 获取项目当前融资信息接口
     * @param body
     * @return
     */
    CommonDto<Map<String,Object>> getProjectFinancingNow(Map<String,Object> body);

    /**
     * 获取项目历史融资信息接口
     * @param body
     * @return
     */
    CommonDto<List<Map<String,Object>>> getProjectFinancingHistory(Map<String,Object> body);

    /**
     * 获取项目团队成员接口
     * @param body
     * @return
     */
    CommonDto<List<Map<String,Object>>> getProjectFinancingTeam(Map<String,Object> body);

    CommonDto<List<Map<String,Object>>> getProjectCompeting(Map<String,Object> body);

    /**
     * 判断项目是否是我的 的接口
     * @param xmid 项目id
     * @param token 用户token
     * @return
     */
    CommonDto<Boolean> judgeProjectIsMine(Integer xmid,String token);
}