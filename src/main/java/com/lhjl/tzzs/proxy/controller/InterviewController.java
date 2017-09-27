package com.lhjl.tzzs.proxy.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InterviewDto;
import com.lhjl.tzzs.proxy.model.Interview;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.service.InterviewService;
import com.lhjl.tzzs.proxy.service.common.JedisCommonService;

import redis.clients.jedis.Jedis;

/**
 * 发起约谈请求
 * @author lmy
 *
 */
@RestController
public class InterviewController {
    private static final Logger log = LoggerFactory.getLogger(FinancingController.class);
    @Resource
    private InterviewService interviewService;
    /**
     *
     * @param projectsId 项目id
     * @param userId     用户id
     * @param desc       约谈内容
     * @return
     */

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
		/*Jedis jedis = jedisCommonService.getJedis();
		String userid = jedis.get("userid");
		interview.setUsersId(Integer.valueOf(userid));*/
        //测试数据
        CommonDto<String> result = new CommonDto<String>();
        try {
            interviewService.insertInterview(interview);
            result.setMessage("约谈成功");
        } catch (Exception e) {
            // TODO: handle exception
            result.setMessage("约谈失败");
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

}