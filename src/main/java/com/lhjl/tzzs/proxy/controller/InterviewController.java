package com.lhjl.tzzs.proxy.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.dto.EventDto;
import com.lhjl.tzzs.proxy.dto.InterviewCommentDto;
import com.lhjl.tzzs.proxy.dto.InterviewDetailsOutputDto;
import com.lhjl.tzzs.proxy.service.UserInfoService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InterviewDto;
import com.lhjl.tzzs.proxy.dto.InterviewListInputDto;
import com.lhjl.tzzs.proxy.model.Interview;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.service.FollowService;
import com.lhjl.tzzs.proxy.service.InterviewService;
import com.lhjl.tzzs.proxy.service.common.JedisCommonService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

/**
 * 发起约谈请求
 * @author lmy
 *
 */
@RestController
public class InterviewController extends GenericController{
    private static final Logger log = LoggerFactory.getLogger(FinancingController.class);
    @Resource
    private InterviewService interviewService;
  
    @Resource
    private FollowService followService;

    @Resource
    private UserInfoService userInfoService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${event.trigger.url}")
    private String eventUrl;

    /**
     *
     * @param projectsId 项目id
     * @param userId     用户id
     * @param desc       约谈内容
     * @return
     */
    
    @ApiOperation(value = "发起约谈接口", notes = "根据用户ID与项目的ID来标注请求的唯一携带发送的")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body", dataType = "String", name = "userId", value = "用户ID", required = true),
    	                 @ApiImplicitParam(paramType = "body", dataType = "String", name = "desc", value = "约谈内容", required = true),
    	                 @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "projects", value = "项目ID", required = true),
    	                 @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "yn", value = "当前项目的状态值", required = true)
    	})
    /**
     * 进行约谈记录跟进状态的更新-zd
     * @param interviewId
     * @param status
     * @param appid
     * @return
     */
    @GetMapping("/v{appid}/update/status")
    public CommonDto<Boolean> updateStatus(Integer id,Integer status,@PathVariable Integer appid){
    	CommonDto<Boolean> result=new CommonDto<>();
    	try {
    		result=interviewService.updateStatus(id,status,appid);
    	}catch(Exception e) {
    		result.setData(false);
    		result.setMessage("服务器内部错误");
    		result.setStatus(500);
    	}
    	return result;
    }
    /**
     * 进行约谈记录批注的添加
     * @param appid
     * @param interviewId
     * @return
     */
    @PostMapping("/v{appid}/add/interviewcomment")
    public CommonDto<Boolean> addInterviewComment(@PathVariable Integer appid,@RequestBody InterviewCommentDto body){
    	this.log.error(appid+"**"+body);
    	CommonDto<Boolean> result=new CommonDto<Boolean>();
    	try {
    		result = interviewService.addComent(appid,body);
    	}catch(Exception e) {
    		result.setData(null);
    		result.setMessage("服务器内部错误");
    		result.setStatus(500);
    	}
    	return result;
    }
    /**
     * 回显约谈记录的详细相关信息-zd
     * @param id 约谈id
     * @param projectId 项目id
     * @param appid
     * @return
     */
    @GetMapping("/v{appid}/echo/interviewinfo")
    //http://localhost:9090/v1/echo/interviewinfo?id=1&projectShortName=玩秘
    public CommonDto<InterviewDetailsOutputDto> echoInterviewInfo(Integer id,String projectShortName,@PathVariable Integer appid){
    	CommonDto<InterviewDetailsOutputDto> result=new CommonDto<InterviewDetailsOutputDto>();
    	try {
    		result = interviewService.echoInterviewInfo(id,projectShortName,appid);
    	}catch(Exception e) {
    		this.log.error(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("服务器内部错误");
    		result.setStatus(500);
    	}
    	return result;
    }
    
    
    
    /**
     * 约谈记录信息列表显示-zd
     * @return
     */
    @PostMapping("/v{appid}/list/interview")  
    public CommonDto<Map<String,Object>> echoInterviewList(@PathVariable("appid") Integer appid,@RequestBody InterviewListInputDto body){
    	CommonDto<Map<String,Object>> result= new CommonDto<Map<String,Object>>();
    	try {
//    		System.err.println(body);
            result= interviewService.getInterviewList(body);
        }catch (Exception e){
        	LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }
    @PostMapping("interview/save")
    public CommonDto<String> insertInterview(@RequestBody InterviewDto  body){
    	String desc =body.getDesc();
    	Integer projectsId =body.getProjectsId();
    	String userId=body.getUserId();
        Interview interview = new Interview();
        int status = 0;
        interview.setStatus(status);
        interview.setProjectsId(projectsId);
        interview.setUserId(userId);
        interview.setDesc(desc);
        interview.setCreateTime(new Date());
        Integer yn =body.getYn();
		/*Jedis jedis = jedisCommonService.getJedis();
		String userid = jedis.get("userid");
		interview.setUsersId(Integer.valueOf(userid));*/
        
        //测试数据
        CommonDto<String> result = new CommonDto<String>();
        try {

            EventDto eventDto = new EventDto();
            eventDto.setFromUser(userId);
            List<Integer> projectIds = new ArrayList<>();
            projectIds.add(projectsId);
            eventDto.setProjectIds(projectIds);
            eventDto.setEventType("TURN_AROUND");
            eventDto.setMessage(desc);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            CommonDto<Integer> resultJG  = userInfoService.getUserInvestmentInstitution(userId);
            List<Integer> investmentInstitusionIds = new ArrayList<>(1);
            investmentInstitusionIds.add(resultJG.getData());
            eventDto.setInvestmentInstitutionsIds(investmentInstitusionIds);
            HttpEntity<EventDto> entity = new HttpEntity<>(eventDto, headers);
            HttpEntity<CommonDto<String>> investors =  restTemplate.exchange(eventUrl+"/trigger/event", HttpMethod.POST,entity,new ParameterizedTypeReference<CommonDto<String>>(){} );


            interviewService.insertInterview(interview);
            interviewService.updateFollowStatus1(yn, projectsId, userId);
            result.setMessage("约谈成功");
            result.setStatus(200);
            result.setData("success");
        } catch (Exception e) {
            // TODO: handle exception
            result.setMessage("约谈失败");
            result.setStatus(404);
            result.setData("fail");
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
    
}