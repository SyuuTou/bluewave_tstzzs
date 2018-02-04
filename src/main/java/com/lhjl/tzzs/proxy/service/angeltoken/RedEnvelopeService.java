package com.lhjl.tzzs.proxy.service.angeltoken;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeResDto;

import java.math.BigDecimal;

public interface RedEnvelopeService {
    CommonDto<String> checkAndAddAngelToken(Integer appId, String token, String senceKey);

    CommonDto<Long> createRedEnvelope(Integer appId, RedEnvelopeDto redEnvelopeDto);

    CommonDto<BigDecimal> checkMaxQuanlity(Integer appId);

    CommonDto<BigDecimal> checkReceiveQuantity(Integer appId, String token);

    CommonDto<BigDecimal> checkRemainingBalance(Integer appId, String token);

    CommonDto<RedEnvelopeResDto> receiveRedEnvelope(Integer appId, Long redEnvelopeId, String token);
}
