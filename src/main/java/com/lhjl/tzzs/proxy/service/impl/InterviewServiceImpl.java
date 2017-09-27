package com.lhjl.tzzs.proxy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhjl.tzzs.proxy.mapper.InterviewMapper;
import com.lhjl.tzzs.proxy.model.Interview;
import com.lhjl.tzzs.proxy.service.InterviewService;

/**
 * 约谈
 * @author PP
 *
 */
@Service
public class InterviewServiceImpl implements InterviewService {

    @Resource
    private InterviewMapper interviewMapper;
    /**
     * 插入约谈记录
     */
    @Override
    @Transactional
    public void insertInterview(Interview interview) {
        interviewMapper.insert(interview);
    }

}