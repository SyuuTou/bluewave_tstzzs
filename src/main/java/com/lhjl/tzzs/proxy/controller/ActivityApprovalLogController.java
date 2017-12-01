package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.ActivityApprovalLogService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class ActivityApprovalLogController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ActivityApprovalLogController.class);

    @Resource
    private ActivityApprovalLogService activityApprovalLogService;


    /**
     * 创建用户活动申请记录接口
     * @param body
     * @return
     */
    @PostMapping("create/activityapproval/log")
    public CommonDto<String> createActicityApprovalLog(@RequestBody Map<String,Object> body){
        CommonDto<String> result  =new CommonDto<>();

        try {
            result = activityApprovalLogService.createActicityApprovalLog(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);

        }

        return result;
    }
}
