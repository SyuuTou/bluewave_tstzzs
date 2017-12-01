package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ActivityApprovalLogMapper;
import com.lhjl.tzzs.proxy.model.ActivityApprovalLog;
import com.lhjl.tzzs.proxy.service.ActivityApprovalLogService;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Service
public class ActivityApprovalLogServiceImpl implements ActivityApprovalLogService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ActivityApprovalLogServiceImpl.class);

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Autowired
    private ActivityApprovalLogMapper activityApprovalLogMapper;

    /**
     * 创建用户活动申请记录接口
     * @param body
     * @return
     */
    @Transactional
    @Override
    public CommonDto<String> createActicityApprovalLog(Map<String, Object> body) {
        CommonDto<String> result  = new CommonDto<>();
        Date now = new Date();
        if (body.get("token") == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("用户token不能为空");

            return result;
        }

        String token = (String) body.get("token");

        Integer userId = userExistJudgmentService.getUserId(token);
        if (userId == -1){
            result.setMessage("用户token无效，请检查");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        ActivityApprovalLog activityApprovalLog = new ActivityApprovalLog();
        activityApprovalLog.setCreateTime(now);
        activityApprovalLog.setUserId(userId);

        activityApprovalLogMapper.insertSelective(activityApprovalLog);

        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");

        return result;
    }
}
