package com.lhjl.tzzs.proxy.controller.angeltoken;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeResDto;
import com.lhjl.tzzs.proxy.service.angeltoken.RedEnvelopeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * 红包相关处理接口
 * @author zhhu
 */
@RestController
public class RedEnvelopeController extends GenericController {

    @Resource(name = "redEnvelopeService")
    private RedEnvelopeService redEnvelopeService;

    /**
     * 检查用户余额
     * @param appId
     * @param token parameter的方式传递
     * @return
     */
    @GetMapping("/v{appId}/check/remaining/balance")
    public CommonDto<BigDecimal> checkRemainingBalance(@PathVariable Integer appId, String token){
        CommonDto<BigDecimal> result = null;

        try {
            result = redEnvelopeService.checkRemainingBalance(appId, token);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;

    }


    /**
     * 令牌赠送接口
     * @param appId 系统ID
     * @param token 用户TOKEN，parameter的方式传递
     * @param senceKey 场景KEY
     * @return
     */
    @PutMapping("/v{appId}/presented/{token}")
    public CommonDto<String> presented(@PathVariable Integer appId, @PathVariable String token, @RequestParam(defaultValue = "INDEX_ANGEL_TOKEN_PRESENTED") String senceKey){

        CommonDto<String>  result = null;

        try {
            result = redEnvelopeService.checkAndAddAngelToken(appId, token, senceKey);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;

    }

    /**
     * 创建红包
     * @param redEnvelopeDto
     * @param appId
     * @return
     */
    @PostMapping("/v{appId}/redenvelope")
    public CommonDto<Long> createRedEnvelope(@RequestBody RedEnvelopeDto redEnvelopeDto, @PathVariable Integer appId){
        CommonDto<Long> result = null;

        try {
            result = redEnvelopeService.createRedEnvelope(appId,redEnvelopeDto);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>(0l,"创建红包异常，请重试。",500);
        }

        return result;

    }

    /**
     * 领取红包
     * @param appId
     * @param redEnvelopeId
     * @param token
     * @return
     */
    @GetMapping("/v{appId}/redenvelope/{redEnvelopeId}")
    public CommonDto<RedEnvelopeResDto> getRedEnvelope(@PathVariable Integer appId, @PathVariable Long redEnvelopeId, String token){

        CommonDto<RedEnvelopeResDto> result = null;

        result = redEnvelopeService.receiveRedEnvelope(appId, redEnvelopeId, token);

        return result;
    }

    /**
     * 检查令牌总数量
     * @param appId
     * @return
     */
    @GetMapping("/v{appId}/redenvelope/quanlity")
    public CommonDto<BigDecimal> checkMaxQuanlity(@PathVariable Integer appId){
        CommonDto<BigDecimal> result = null;


        result = redEnvelopeService.checkMaxQuanlity(appId);


        return result;
    }

    /**
     * 检查用户可领取数量
     * @param appId
     * @param token
     * @return
     */
    @GetMapping("/v{appId}/redenvelope/receive/quanlity")
    public CommonDto<BigDecimal> checkReceiveQuantity(@PathVariable Integer appId, String token){
        CommonDto<BigDecimal> result = null;


        result = redEnvelopeService.checkReceiveQuantity(appId,token);


        return result;
    }

}
