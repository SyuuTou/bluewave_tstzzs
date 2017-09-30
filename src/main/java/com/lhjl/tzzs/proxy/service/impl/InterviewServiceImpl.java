package com.lhjl.tzzs.proxy.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.FollowMapper;
import com.lhjl.tzzs.proxy.mapper.InterviewMapper;
import com.lhjl.tzzs.proxy.model.Follow;
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
    
    
    @Resource
    private FollowMapper followMapper;
    /**
     * 插入约谈记录
     */
    @Override
    @Transactional
    public void insertInterview(Interview interview) {
        interviewMapper.insert(interview);
    }
    
    
	@Override
	@Transactional
	public CommonDto<String> updateFollowStatus1(Integer yn, Integer projectId, String userId) {
		CommonDto<String> result =new CommonDto<String>();
		Follow follow = new Follow();
        follow.setProjectsId(projectId);
        List<Follow> list = followMapper.select(follow);
        if(list.size() == 0|| list.get(0) == null ){
            yn = 1;
            follow.setCreateTime(new Date());
            follow.setProjectsId(projectId);
            follow.setStatus( yn);
            follow.setUserId(userId);
            followMapper.insert(follow);
            result.setStatus(200);
            result.setData("sucess");
        }else{
            if( yn == 0){
            	 yn= 1;
                follow.setCreateTime(new Date());
                follow.setProjectsId(projectId);
                follow.setStatus( yn);
                follow.setUserId(userId);
                followMapper.insert(follow);
                result.setStatus(200);
                result.setData("sucess"); 
            }
        }
		return result;
       }
	}