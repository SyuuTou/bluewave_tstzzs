package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.model.AdminProjectRatingLog;
import com.lhjl.tzzs.proxy.model.MetaDataSourceType;
import com.lhjl.tzzs.proxy.model.MetaFollowStatus;
import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.model.ProjectFollowStatus;


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

    /**
     * 获取项目管理员列表接口
     * @param body
     * @return
     */
    CommonDto<List<ProjectAdministratorOutputDto>> getProjectAdministractorList(Map<String,Integer> body);

    /**
     * 通过项目id获取项目信息接口
     * @param body
     * @return
     */
    CommonDto<ProjectComplexOutputDto> getProjectComplexInfo(Map<String,Integer> body);
    /**
     * 天使投资指数后台的项目列表
     * @param appid
     * @param body
     * @return
     */
	CommonDto<Map<String, Object>> listProInfos(Integer appid, ProjectsListInputDto body);
	/**
	 * 更新项目的跟进状态
	 * @param appid
	 * @param body
	 * @return
	 */
	CommonDto<Boolean> updateFollowStatus(Integer appid, ProjectsUpdateInputDto body);
	/**
	 * 获取项目跟进状态元数据
	 * @param appid
	 * @return
	 */
	CommonDto<List<MetaFollowStatus>> getFollowStatusSource(Integer appid);
	/**
	 * 获取项目来源的元数据
	 * @param appid
	 * @return
	 */
	CommonDto<List<MetaDataSourceType>> getProjectsSource(Integer appid);
	/**
	 * 获取项目等级元数据
	 * @param appid
	 * @return
	 */
	CommonDto<List<AdminProjectRatingLog>> getProjectsRatingStages(Integer appid);
	/**
	 * 获取项目的融资状态
	 * @param appid
	 */
	CommonDto<List<String>> getFinancingStatus(Integer appid);

	/**
	 * 获取项目的融资历史记录
	 * @param appid
	 * @param projectId 项目id
	 * @return
	 */
	CommonDto<List<ProjectFinancingLog>> getFinancingLogs(Integer appid, Integer projectId);
	/**
	 * 删除单条的融资历史记录信息
	 * @param appid
	 * @param financiingLogId 融资历史记录的标识id
	 * @return
	 */
	CommonDto<Boolean> removeFinancingLogById(Integer appid, FinancingLogDelInputDto body);

	/**
	 * 根据项目id获取项目跟进状态的接口
	 * @param projectId
	 * @param appid
	 * @return
	 */
	CommonDto<ProjectFollowStatus> getFollowStatus(Integer projectId, Integer appid);
	/**
	 * 更新融资历史的信息
	 * @param appid
	 * @param body
	 * @return
	 */
	CommonDto<Boolean> updateFinancingLog(Integer appid, ProjectFinancingLog body);
}