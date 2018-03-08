package com.lhjl.tzzs.proxy.controller.angeltoken;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeGroupDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeResDto;
import com.lhjl.tzzs.proxy.service.angeltoken.RedEnvelopeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * 红包相关处理接口
 * @author zhhu
 */
@RestController
public class RedEnvelopeController extends GenericController {

    @Resource(name = "redEnvelopeService")
    private RedEnvelopeService redEnvelopeService;


    /**
     * 解析群ID
     * @param appId
     * @param redEnvelopeId
     * @param token
     * @param groupDto
     * @return
     */
    @PostMapping("/v{appId}/redenvelope/{redEnvelopeId}")
    public CommonDto<String> saveRedEnvelopeWechatGroupId(@PathVariable Integer appId, @PathVariable String redEnvelopeId, @RequestParam String token, @RequestBody RedEnvelopeGroupDto groupDto){

        CommonDto<String> result = null;

        try {
            result = redEnvelopeService.resolveWechatGroupId(appId, redEnvelopeId, token, groupDto);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
        }

        return result;
    }


    /**
     * 为发送群定义一个唯一key
     * @param appId
     * @param redEnvelopeId
     * @param token
     * @return
     */
    @GetMapping("/v{appId}/{redEnvelopeId}/redEnvelopeKey")
    public CommonDto<String> getRedEnvelopeWechatGroupKey(@PathVariable Integer appId, @PathVariable String redEnvelopeId, @RequestParam String token ){
        CommonDto<String> result = null;

        try {
            result = redEnvelopeService.getRedEnvelopeWechatGroupKey(appId, redEnvelopeId, token);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
        }

        return result;
    }

    /**
     * 检查用户令牌余额
     * @param appId
     * @param token parameter的方式传递
     * @return
     */
    @GetMapping("/v{appId}/check/remaining/balance")
    public CommonDto<BigDecimal> checkRemainingBalance(@PathVariable Integer appId , String token){
        CommonDto<BigDecimal> result = null;

        try {
            result = redEnvelopeService.checkRemainingBalance(appId, token, 1);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;

    }

    /**
     * 检查用户余额
     * @param appId
     * @param currency 币种，0：人民币，1：令牌
     * @param token parameter的方式传递
     * @return
     */
    @GetMapping("/v{appId}/check/{currency}/remaining/balance")
    public CommonDto<BigDecimal> checkRemainingBalance(@PathVariable Integer appId,@PathVariable Integer currency, String token){
        CommonDto<BigDecimal> result = null;

        try {
            result = redEnvelopeService.checkRemainingBalance(appId, token, currency);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;

    }

    /**
     * 获取邀请红包的金额和数量限制
     * @param appId
     * @param token
     * @return
     */
    @GetMapping("/v{appId}/invitationed/limit")
    public CommonDto<Map<String,BigDecimal>> getInvitationedLimit(@PathVariable Integer appId, String token){
        CommonDto<Map<String,BigDecimal>> result = null;

        result = redEnvelopeService.getInvitationedLimit(appId, token);

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
    public CommonDto<String> createRedEnvelope(@RequestBody RedEnvelopeDto redEnvelopeDto, @PathVariable Integer appId){
        CommonDto<String> result = null;

        try {
            result = redEnvelopeService.createRedEnvelope(appId,redEnvelopeDto);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>("","创建红包异常，请重试。",500);
        }

        return result;

    }

    /**
     * 获取红包基本信息
     * @param appId
     * @param unionId
     * @param token
     * @return
     */
    @GetMapping("/v{appId}/reredenvelope/{unionId}/info")
    public CommonDto<RedEnvelopeResDto> getRedEnvelopeInfo(@PathVariable Integer appId, @PathVariable String unionId, String token){
        CommonDto<RedEnvelopeResDto> result = null;

        result = redEnvelopeService.getRedEnvelopeInfo(appId, unionId, token);

        return result;

    }

    /**
     * 统计数据
     * @return
     */
    @GetMapping("/v{appId}/redenvelope/statistices")
    public CommonDto<Map<String, Integer>>  getStatisticesRedEnvelope(@PathVariable Integer appId){

        CommonDto<Map<String, Integer>> result = null;

        result = redEnvelopeService.getStatisticesRedEnvelope();

        return result;
    }

    /**
     * 领取红包
     * @param appId
     * @param
     * @param token
     * @return
     */
    @GetMapping("/v{appId}/redenvelope/{unionId}")
    public CommonDto<RedEnvelopeResDto> getRedEnvelope(@PathVariable Integer appId, @PathVariable String unionId, String token, String wechatGroupUnionKey){

        CommonDto<RedEnvelopeResDto> result = null;

        try {
            result = redEnvelopeService.receiveRedEnvelope(appId, unionId, token, wechatGroupUnionKey);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    /**
     * 获取领取红包的人发的红包列表
     * @param appId
     * @param token
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/v{appId}/redenvelope")
    public CommonDto<List<RedEnvelopeResDto>> queryRedEnvelopeAll(@PathVariable Integer appId, String token, Integer pageNo, Integer pageSize){
        CommonDto<List<RedEnvelopeResDto>> result =null;

        result = redEnvelopeService.queryRedEnvelopeAllByToken(appId, token, pageNo, pageSize);

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


        try {
            result = redEnvelopeService.checkMaxQuanlity(appId);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }


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


        try {
            result = redEnvelopeService.checkReceiveQuantity(appId,token);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }


        return result;
    }

}
