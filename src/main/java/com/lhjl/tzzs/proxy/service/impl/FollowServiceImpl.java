package com.lhjl.tzzs.proxy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.mapper.FollowMapper;
import com.lhjl.tzzs.proxy.model.Follow;
import com.lhjl.tzzs.proxy.service.FollowService;
import com.lhjl.tzzs.proxy.service.common.JedisCommonService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
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

    @Autowired
    private RestTemplate restTemplate;

    @Value("${event.trigger.url}")
    private String eventUrl;

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

            EventDto eventDto = new EventDto();
            eventDto.setFromUser(userId);
            List<Integer> projectIds = new ArrayList<>();
            projectIds.add(projectId);
            eventDto.setProjectIds(projectIds);
            eventDto.setEventType("CONCERN");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<EventDto> entity = new HttpEntity<>(eventDto, headers);
            HttpEntity<CommonDto<String>> investors =  restTemplate.exchange(eventUrl+"/trigger/event", HttpMethod.POST,entity,new ParameterizedTypeReference<CommonDto<String>>(){} );
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