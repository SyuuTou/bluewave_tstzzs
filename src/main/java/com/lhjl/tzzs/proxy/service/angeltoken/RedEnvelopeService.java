package com.lhjl.tzzs.proxy.service.angeltoken;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeGroupDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeResDto;

import java.math.BigDecimal;
import java.util.Map;

public interface RedEnvelopeService {
    CommonDto<String> checkAndAddAngelToken(Integer appId, String token, String senceKey);

    CommonDto<String> createRedEnvelope(Integer appId, RedEnvelopeDto redEnvelopeDto);

    CommonDto<BigDecimal> checkMaxQuanlity(Integer appId);

    CommonDto<BigDecimal> checkReceiveQuantity(Integer appId, String token);

    CommonDto<BigDecimal> checkRemainingBalance(Integer appId, String token);

    CommonDto<RedEnvelopeResDto> receiveRedEnvelope(Integer appId, String unionId, String token, String unionKey);

    CommonDto<Map<String,BigDecimal>> getInvitationedLimit(Integer appId, String token);

    CommonDto<String> resolveWechatGroupId(Integer appId, String redEnvelopeId, String token, RedEnvelopeGroupDto groupDto);

    CommonDto<String> getRedEnvelopeWechatGroupKey(Integer appId, String redEnvelopeId, String token);
}
