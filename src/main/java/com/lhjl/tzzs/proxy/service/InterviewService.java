package com.lhjl.tzzs.proxy.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InterviewCommentDto;
import com.lhjl.tzzs.proxy.dto.InterviewDetailsOutputDto;
import com.lhjl.tzzs.proxy.dto.InterviewListInputDto;
import com.lhjl.tzzs.proxy.dto.UpdateModifyInputDto;
import com.lhjl.tzzs.proxy.model.Interview;

/**
 * 约谈
 * @author PP
 *
 */
public interface InterviewService {

    /**
     * 插入约谈记录
     */
    void insertInterview(Interview interview);
    CommonDto<String> updateFollowStatus1(@Param("yn") Integer yn,@Param("projectId")Integer projectId,@Param("userId")String userId);
    
    
    /**
     * 更新约谈记录的跟进状态
     * @param interviewId 约谈记录的唯一标志Id
     * @param status
     * @param appid 待使用
     */
    CommonDto<Boolean> updateStatus(UpdateModifyInputDto reqBody, Integer appid);
    
    /**
     * 回显约谈记录相关的详细信息
     * @param id 约谈记录的id
     * @param appid
     * @return
     */
    CommonDto<InterviewDetailsOutputDto> echoInterviewInfo(Integer id,String projectShortName, Integer appid);
    /**
     * 进行约谈批注的添加
     * @param appid
     * @param body
     * @return
     */
	CommonDto<Boolean> addComent(Integer appid, InterviewCommentDto body);
	/**
	 * 获取访谈页面的分页列表
	 * @param body
	 * @return
	 */
	CommonDto<Map<String, Object>> getInterviewList(InterviewListInputDto body);
}