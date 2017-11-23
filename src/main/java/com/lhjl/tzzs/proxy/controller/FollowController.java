package com.lhjl.tzzs.proxy.controller;

import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.dto.EventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.FollowService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 关注请求，取消关注记录
 * @author lmy
 *
 */
@RestController
public class FollowController {

    private static final Logger log = LoggerFactory.getLogger(FinancingController.class);

    @Resource
    private FollowService followService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${event.trigger.url}")
    private String eventUrl;
    /**
     *
     * @param status  关注状态0：未关注 1 已关注
     * @param projectId  项目id
     * @param userId  用户id
     * @return
     */
    @ApiOperation(value = "关注接口", notes = "根据用户ID与项目的ID，以及项目的目前状态来标注请求唯一")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "status",paramType="path" , value = "项目目前状态", required = true, dataType = "Integer"),
    	  @ApiImplicitParam(name = "userId",paramType="path" , value = "用户ID", required = true, dataType = "String"),
          @ApiImplicitParam(name = "projectId",paramType="path",  value = "项目ID", required = true, dataType ="Integer")})
    @GetMapping("search/project/{status}/{projectId}/{userId}")
    public CommonDto<String> updateFollowStatus(@PathVariable Integer status,
                                                @PathVariable Integer projectId,@PathVariable String userId){
        CommonDto<String> result = new CommonDto<String>();
        
        try {

            followService.updateFollowStatus(status,projectId,userId);

            EventDto  eventDto = new EventDto();
            eventDto.setFromUser(userId);
            List<Integer> projectIds = new ArrayList<>();
            projectIds.add(projectId);
            eventDto.setProjectIds(projectIds);
            eventDto.setEventType("CONCERN");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<EventDto> entity = new HttpEntity<>(eventDto, headers);
            HttpEntity<CommonDto<String>> investors =  restTemplate.exchange(eventUrl+"/trigger/event", HttpMethod.POST,entity,new ParameterizedTypeReference<CommonDto<String>>(){} );

            result.setMessage("操作成功");
        } catch (Exception e) {
            // TODO: handle exception
            result.setMessage("操作失败");
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
}