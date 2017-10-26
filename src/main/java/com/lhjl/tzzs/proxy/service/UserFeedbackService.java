package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;

import java.util.Map;

public interface UserFeedbackService {
    CommonDto<String> useraddFeedback(Map<String,String> body);
}
