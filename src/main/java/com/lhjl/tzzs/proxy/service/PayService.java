package com.lhjl.tzzs.proxy.service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.PayRequestBody;

import java.util.Map;

public interface PayService {
    CommonDto<Map<String,String>> generatePayInfo(PayRequestBody payRequestBody, Integer appId);

    void payNotifyHandler(WxPayOrderNotifyResult result);
}
