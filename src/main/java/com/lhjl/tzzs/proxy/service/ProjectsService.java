package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.model.AdminProjectRatingLog;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsAddressPart;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsProject;
import com.lhjl.tzzs.proxy.model.MetaDataSourceType;
import com.lhjl.tzzs.proxy.model.MetaFollowStatus;
import com.lhjl.tzzs.proxy.model.MetaJobType;
import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.model.ProjectFollowStatus;
import com.lhjl.tzzs.proxy.model.ProjectProgress;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.model.Recruitment;
import com.lhjl.tzzs.proxy.model.RecruitmentInfo;
import com.lhjl.tzzs.proxy.model.Users;


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
	CommonDto<Boolean> removeFinancingLogById(Integer appid, Integer id);

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
	/**
	 * 返回单条融资历史记录的详细信息
	 * @param appid
	 * @param financingLodId 融资历史记录的id
	 * @return
	 */
	CommonDto<List<InvestmentInstitutionsProject>> getFinancingLogDetails(Integer appid, Integer financingLodId);
	/**
     * 移除项目的融资历史单阶段对应的投资机构信息
     * @param appid
     * @param id  investment_institutions_project表中的主键id
     * @return
     */
	CommonDto<Boolean> removeSingleInvestment(Integer appid, Integer id);
	/**
	 * 更新融资历史相关的投资机构信息
	 * @param appid
	 * @param body 融资历史单阶段对应的投资机构信息
	 * @return
	 */
	CommonDto<Boolean> updateRelativeInvestmentInfo(Integer appid, InvestmentInstitutionsProject body);
	/**
     * 回显 公司简介 以及 投资亮点
     * @param appid
     * @param proId
     * @return
     */
	CommonDto<Projects> getProInfoById(Integer appid, Integer proId);
	/**
     * 更新公司相关信息(主要用于更新项目简介 以及 投资亮点)
     * @param appid
     * @param proId
     * @return
     */
	CommonDto<Boolean> updateProInfos(Integer appid, Projects body);
	/**
     * 项目公司的列表信息
     * @param appid 扩展字段
     * @param proType 项目的类别(根据不同的项目类别来列举不同项目的分部信息)
     * @param proId 项目或者投资机构的id
     * @return
     */
	CommonDto<List<InvestmentInstitutionsAddressPart>> listProPartsByCompanyIdAndProtype(Integer appid, Integer companyType,Integer companyId);
	/**
     * 根据id删除分部信息
     * @param appid
     * @param partId 分部id
     * @return
     */
	CommonDto<Boolean> removePartInfoById(Integer appid, Integer partId);
	/**
	 * 保存或者更新地址分部的信息
	 * @param appid
	 * @param body
	 * @return
	 */
	CommonDto<Boolean> saveOrUpdayePart(Integer appid, InvestmentInstitutionsAddressPart body);
	 /**
     * 获取岗位类型的元数据
     * @param appid
     * @return
     */
	CommonDto<List<MetaJobType>> getMetaJobTypes(Integer appid);
	/**
     * 保存或者公司招聘信息
     * @param appid
     * @param body 公司的招聘信息
     * @return
     */
	CommonDto<Boolean> saveOrUpdateRecruitment(Integer appid, Recruitment body);
	/**
     * 根据id删除招聘信息
     * @param appid
     * @param partId 招聘信息id
     * @return
     */
	CommonDto<Boolean> removeRecruInfoById(Integer appid, Integer id);
	/**
     * 招聘职位信息列表
     * @param appid
     * @param proId 项目id
     * @return
     */
	CommonDto<List<Recruitment>> listRecruInfos(Integer appid, Integer proId);
	/**
     * 招聘需求信息回显
     * @param appid
     * @param proId 项目id
     * @return
     */
	CommonDto<RecruitmentInfo> echoRequirementInfo(Integer appid, Integer proId);
	/**
     * 招聘需求信息编辑
     * @param appid
     * @param body 招聘需求请求体
     * @return
     */
	CommonDto<Boolean> editRequirementInfo(Integer appid, RecruitmentInfo body);
	/**
     * 公司进展列表
     * @param appid
     * @param companyId 公司id
     * @return
     */
	CommonDto<List<ProjectProgress>> listProProgress(Integer appid, Integer companyId);
	/**
     * 增加公司进展的消息
     * @param appid
     * @param body
     * @return
     */
	CommonDto<Boolean> saveOrUpdateProgressInfo(Integer appid, ProjectProgress body);
	 /**
     * 根据id删除进展的信息
     * @param appid
     * @param id 进展的id
     * @return
     */
	CommonDto<Boolean> removeProgressInfoById(Integer appid, Integer id);
	/**
     * 投资方的智能搜索
     * @param appid
     * @param keyword
     * @return
     */
	CommonDto<List<InvestmentInstitutions>> intelligentSearch(Integer appid, String keyword);
	/**
     * 根据用户id获取用户信息
     * @param appid
     * @param userId
     * @return
     */
	CommonDto<Users> getUserById(Integer appid, Integer userId);
	
}