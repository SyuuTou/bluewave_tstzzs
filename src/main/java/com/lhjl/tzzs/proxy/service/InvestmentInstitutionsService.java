package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ImageHandlerDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionComplexOutputDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionSearchOutputDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto2;
import com.lhjl.tzzs.proxy.dto.ProjectRatingDto;
import com.lhjl.tzzs.proxy.dto.ProjectsUpdateInputDto;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsAddressPart;
import com.lhjl.tzzs.proxy.model.MetaProjectStage;
import com.lhjl.tzzs.proxy.model.MetaSegmentation;

import net.bytebuddy.description.type.TypeDescription.Generic;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface InvestmentInstitutionsService {
	
	/**
	 * 获取所有的投资阶段信息-zd
	 * @return 投资阶段信息list
	 */
	CommonDto<List<MetaProjectStage>> listInvestementStages();
	/**
	 * 获取所有的投资领域信息-zd
	 * @return 投资领域信息list
	 */
	CommonDto<List<MetaSegmentation>> listInvestementFields();
    /**
     * 根据机构id获取机构所有分部信息-zd
     * @param investmentInstitutionId
     * @return
     */
	CommonDto<List<InvestmentInstitutionsAddressPart>> listAllAddressPartsById(Integer investmentInstitutionId);
	
	
	
    /**
     * 根据机构id获取机构信息的接口
     * @param body
     * @return
     */
    CommonDto<InvestmentInstitutionComplexOutputDto> getInvestmentInstitutionsComlexInfo(Map<String,Integer> body);

    /**
     * 根据输入词搜索机构信息接口
     * @param inputsWords 输入的词
     * @return
     */
    CommonDto<List<InvestmentInstitutionSearchOutputDto>> getInstitutionIntelligentSearch(String inputsWords,Integer pageSize);

    /**
     *  读取机构详情的接口
     * @param institutionId
     * @return
     */
    CommonDto<Map<String,Object>> findInstitutionDetails(Integer institutionId);

    /**
     * 读取机构的筛选条件的接口
     * @param institutionId
     * @return
     */
    CommonDto<Map<String,Object>> findFliterInfo(Integer institutionId);

    InputStream imageHandler(ImageHandlerDto reqDto);
	/**
	 * 回显机构信息表单的详细数据
	 * @param token
	 * @param appid
	 * @return
	 */
	CommonDto<InvestmentInstitutionsDto2> echoinstiinfo(Integer id,Integer appid);
	/**
	 * 执行相关机构的更新操作
	 * @param appid 地址传递过来的appid
	 * @param body 传递过来的表单封装体，包含token
	 * @return
	 */
	CommonDto<Boolean> saveOrUpdate(Integer appid,InvestmentInstitutionsDto2 body);
	/**
	 * 机构信息的评级
	 * @param body
	 * @return
	 */
	CommonDto<String> institutionRating(ProjectRatingDto body);
	/**
	 * 机构跟进状态更新
	 * @param appid
	 * @param body
	 * @return
	 */
	CommonDto<Boolean> updateFollowStatus(Integer appid, ProjectsUpdateInputDto body);
	
	
	
	
}
