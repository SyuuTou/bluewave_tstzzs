package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.UserFeedbackMapper;
import com.lhjl.tzzs.proxy.model.UserFeedback;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.service.UserFeedbackService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Service
public class UserFeedbackImpl implements UserFeedbackService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserFeedbackImpl.class);

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Resource
    private UserFeedbackMapper userFeedbackMapper;

    @Override
    public CommonDto<String> useraddFeedback(Map<String,String> body){
        CommonDto<String> result  = new CommonDto<>();
        String token = body.get("token");
        String feedback = body.get("feedback");

        if (token == null || "".equals(token) || "undefined".equals(token)){
            result.setStatus(50001);
            result.setMessage("用户token不能为空");
            result.setData(null);

            return result;
        }

        if (feedback == null || "".equals(feedback) || "undefined".equals(feedback)){
            result.setStatus(50001);
            result.setMessage("请至少填写两个字");
            result.setData(null);

            return result;
        }

        Integer userid = userExistJudgmentService.getUserId(token);

        if (userid == -1){
            result.setStatus(50001);
            result.setMessage("用户token不存在，请检查token");
            result.setData(null);

            return result;
        }
        Date now =new Date();

        UserFeedback userFeedback =new UserFeedback();
        userFeedback.setCreatTime(now);
        userFeedback.setFeedback(feedback);
        userFeedback.setUserid(userid);

        userFeedbackMapper.insert(userFeedback);

        result.setData(null);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }
}
