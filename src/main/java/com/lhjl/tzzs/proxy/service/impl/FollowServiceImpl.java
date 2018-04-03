package com.lhjl.tzzs.proxy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.EventDto;
import com.lhjl.tzzs.proxy.mapper.UserProjectsMapper;
import com.lhjl.tzzs.proxy.mapper.UserTokenMapper;
import com.lhjl.tzzs.proxy.model.UserProjects;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.service.UserInfoService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.mapper.FollowMapper;
import com.lhjl.tzzs.proxy.model.Follow;
import com.lhjl.tzzs.proxy.service.FollowService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
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

    @Resource
    private UserInfoService userInfoService;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private UserProjectsMapper userProjectsMapper;

    @Value("${event.trigger.url}")
    private String eventUrl;

    @Transactional
    @Override
    public void updateFollowStatus(Integer status, Integer projectId,String userId) {
        Follow follow = new Follow();
        follow.setProjectsId(projectId);
        List<Follow> list = followMapper.select(follow);
        if(list.size() == 0|| list.get(0) == null ){
            addConcernEvent(projectId, userId, follow);
        }else{
            if(status == 0){
                addConcernEvent(projectId, userId, follow);
            }else{
                follow.setStatus(0);
                follow.setProjectsId(projectId);
                follow.setUserId(userId);
                follow.setCreateTime(new Date());
                followMapper.updateFollowStatus(follow);
                UserToken userToken = new UserToken();
                userToken.setToken(userId);
                userToken = userTokenMapper.selectOne(userToken);

                UserProjects userProjects = new UserProjects();
                userProjects.setUserId(userToken.getUserId());
                userProjects.setSendProjectId(projectId);
                userProjects = userProjectsMapper.selectOne(userProjects);
                if (null == userProjects) {
                    userProjects.setIsdel(1);
                    userProjectsMapper.updateByPrimaryKey(userProjects);
                }

            }

        }
    }

    private void addConcernEvent(Integer projectId, String userId, Follow follow) {
        Integer status;
        status = 1;
        follow.setCreateTime(new Date());
        follow.setProjectsId(projectId);
        follow.setStatus(status);
        follow.setUserId(userId);
        followMapper.insert(follow);
        UserToken userToken = new UserToken();
        userToken.setToken(userId);
        userToken = userTokenMapper.selectOne(userToken);
        UserProjects userProjects = new UserProjects();
        userProjects.setUserId(userToken.getUserId());
        userProjects.setSendProjectId(projectId);
        userProjects = userProjectsMapper.selectOne(userProjects);
        if (null == userProjects) {
            userProjects.setIsdel(0);
            userProjectsMapper.updateByPrimaryKey(userProjects);
        }else {

            userProjects = new UserProjects();
            userProjects.setSourcePlatformId(1);//项目来源平台
            userProjects.setActionId(2);//项目关注
            userProjects.setSendProjectId(projectId);
            userProjects.setUpdateTime(DateTime.now().toDate());
            userProjects.setCreateTime(DateTime.now().toDate());
            userProjects.setUserId(userToken.getUserId());
            userProjects.setYn(1);
            userProjects.setIsdel(0);
            userProjectsMapper.insert(userProjects);

        }
        sendConcernEvent(projectId, userId);
    }

    private void sendConcernEvent(Integer projectId, String userId) {
        EventDto eventDto = new EventDto();
        eventDto.setFromUser(userId);
        List<Integer> projectIds = new ArrayList<>();
        projectIds.add(projectId);
        eventDto.setProjectIds(projectIds);
        eventDto.setEventType("CONCERN");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        CommonDto<Integer> result  = userInfoService.getUserInvestmentInstitution(userId);
        List<Integer> investmentInstitusionIds = new ArrayList<>(1);
        investmentInstitusionIds.add(result.getData());
        eventDto.setInvestmentInstitutionsIds(investmentInstitusionIds);

        HttpEntity<EventDto> entity = new HttpEntity<>(eventDto, headers);
        HttpEntity<CommonDto<String>> investors =  restTemplate.exchange(eventUrl+"/trigger/event", HttpMethod.POST,entity,new ParameterizedTypeReference<CommonDto<String>>(){} );
    }
}