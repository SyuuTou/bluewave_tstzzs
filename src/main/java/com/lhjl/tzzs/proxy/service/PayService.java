package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.PayRequestBody;

import java.util.Map;

public interface PayService {
    CommonDto<Map<String,String>> generatePayInfo(PayRequestBody payRequestBody);
}
