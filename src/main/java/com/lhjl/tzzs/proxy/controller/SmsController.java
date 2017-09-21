package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.common.SmsCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SmsController {
    private static final Logger log = LoggerFactory.getLogger(SmsController.class);

    @Resource
    private SmsCommonService smsCommonService;

    /**
     * 发送短信接口
     * @param userId 用户ID
     * @param phoneNum  手机号
     * @return
     */
    @GetMapping("send/sms/{userId}/{phoneNum}")
    public CommonDto<String> sendSMS(@PathVariable String userId, @PathVariable String phoneNum){

        CommonDto<String> result = new CommonDto<String>();

        try {
            result = smsCommonService.sendSMS(userId,phoneNum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * 验证短信接口
     * @param userId   用户ID
     * @param phoneNum 手机号
     * @param code 验证码
     * @return
     */
    @GetMapping("send/sms/{userId}/{phoneNum}/{code}")
    public CommonDto<String> verifySMS(@PathVariable String userId, @PathVariable String phoneNum, @PathVariable String code){

        CommonDto<String> result = new CommonDto<String>();

        try {
            result = smsCommonService.verifySMS(userId,phoneNum,code);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}
