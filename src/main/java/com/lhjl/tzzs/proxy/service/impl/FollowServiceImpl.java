package com.lhjl.tzzs.proxy.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.mapper.FollowMapper;
import com.lhjl.tzzs.proxy.model.Follow;
import com.lhjl.tzzs.proxy.service.FollowService;
import com.lhjl.tzzs.proxy.service.common.JedisCommonService;

import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
/**
 * 关注
 * @author PP
 *
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Resource
    private FollowMapper followMapper;

    @Transactional
    @Override
    public void updateFollowStatus(Integer status, Integer projectId,String userId) {
        Follow follow = new Follow();
        follow.setProjectsId(projectId);
        List<Follow> list = followMapper.select(follow);
        if(list.size() == 0|| list.get(0) == null ){
            status = 1;
            follow.setCreateTime(new Date());
            follow.setProjectsId(projectId);
            follow.setStatus(status);
            follow.setUserId(userId);
            followMapper.insert(follow);
        }else{
            if(status == 0){
                status = 1;
                follow.setCreateTime(new Date());
                follow.setProjectsId(projectId);
                follow.setStatus(status);
                follow.setUserId(userId);
                followMapper.insert(follow);
            }else{
                follow.setStatus(0);
                follow.setProjectsId(projectId);
                follow.setUserId(userId);
                follow.setCreateTime(new Date());
                followMapper.updateFollowStatus(follow);

            }

        }
    }
}