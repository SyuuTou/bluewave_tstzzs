package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;

public interface SendTemplateService {

    /**
     * 发模板消息的接口
     * @param userId 用户id
     * @param status 1表示失败时候的发送模板,2表示成功后的模板
     * @param formId 用户formid
     * @param message 发送消息内容
     * @param title 消息标题
     * @return
     */
    CommonDto<String> sendTemplateByFormId(Integer userId, Integer status, String formId,String message,String title);
}
