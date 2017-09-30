package com.lhjl.tzzs.proxy.service;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.dto.CommonDto;
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
}