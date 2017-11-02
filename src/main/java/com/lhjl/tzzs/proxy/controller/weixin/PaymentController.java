package com.lhjl.tzzs.proxy.controller.weixin;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyCoupon;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxEntPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxEntPayResult;
import com.github.binarywang.wxpay.bean.result.WxPayBaseResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.google.gson.Gson;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.PayRequestBody;
import com.lhjl.tzzs.proxy.service.PayService;
import com.lhjl.tzzs.proxy.utils.ReturnModel;
import com.lhjl.tzzs.proxy.utils.Sha1Util;
import com.lhjl.tzzs.proxy.utils.XMLUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 微信支付Controller
 * <p>
 * Created by FirenzesEagle on 2016/6/20 0020.
 * Email:liumingbo2008@gmail.com
 */
@RestController
@RequestMapping(value = "wxPay")
public class PaymentController extends GenericController {

  @Resource(name = "idataVCPayService")
  private PayService idataVCPayService;

  @Autowired
  private WxPayConfig payConfig;
  @Autowired
  private WxPayService payService;


  /**
   * 用于返回预支付的结果 WxMpPrepayIdResult，一般不需要使用此接口
   *
   * @param response
   * @param request
   * @throws WxErrorException
   */
  @RequestMapping(value = "getPrepayIdResult")
  public void getPrepayId(HttpServletResponse response,
                          HttpServletRequest request) throws WxErrorException, WxPayException {
    WxPayUnifiedOrderRequest payInfo = WxPayUnifiedOrderRequest.newBuilder()
      .openid(request.getParameter("openid"))
      .outTradeNo(request.getParameter("out_trade_no"))
      .totalFee(Integer.valueOf(request.getParameter("total_fee")))
      .body(request.getParameter("body"))
      .tradeType(request.getParameter("trade_type"))
      .spbillCreateIp(request.getParameter("spbill_create_ip"))
      .notifyURL("")
      .build();
    this.logger
      .info("PartnerKey is :" + this.payConfig.getMchKey());
    WxPayUnifiedOrderResult result = this.payService.unifiedOrder(payInfo);
    this.logger.info(new Gson().toJson(result));
    renderString(response, result);



  }

  /**
   * 返回前台H5调用JS支付所需要的参数，公众号支付调用此接口
   *
   * @param response
   * @param request
   */
  @PostMapping(value = "getJSSDKPayInfo")
  public CommonDto<Map<String, String>> getJSSDKPayInfo(@RequestBody PayRequestBody payRequestBody,
                                   HttpServletResponse response,
                                   HttpServletRequest request) {

    CommonDto<Map<String, String>> commonDto = new CommonDto<>();


    try {
      commonDto = idataVCPayService.generatePayInfo(payRequestBody);
    } catch (Exception e) {
      e.printStackTrace();
      commonDto.setStatus(500);
      commonDto.setMessage(e.getMessage());
    }

    return commonDto;

  }

  /**
   * 微信通知支付结果的回调地址，notify_url
   *
   * @param request
   * @param response
   */
  @ResponseBody
  @RequestMapping("/wxnotify")
  public String payNotify(HttpServletRequest request, HttpServletResponse response) {

    try {

      String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
      WxPayOrderNotifyResult result = payService.parseOrderNotifyResult(xmlResult);
      // 结果正确
      String orderId = result.getOutTradeNo();
      String tradeNo = result.getTransactionId();
      String totalFee = WxPayBaseResult.feeToYuan(result.getTotalFee());
      //自己处理订单的业务逻辑，需要判断订单是否已经支付过，否则可能会重复调用
      idataVCPayService.payNotifyHandler(result);
      this.logger.info("支付通知：{}", result.toString());
      return WxPayNotifyResponse.success("处理成功!");
    } catch (Exception e) {
      this.logger.error("微信回调结果异常,异常原因{}", e.getMessage());
      return WxPayNotifyResponse.fail(e.getMessage());
    }
  }

  @RequestMapping(value = "entPay")
  public void payToIndividual(HttpServletResponse response,
                              HttpServletRequest request) {
    WxEntPayRequest wxEntPayRequest = new WxEntPayRequest();
    wxEntPayRequest.setAppid(payConfig.getAppId());
    wxEntPayRequest.setMchId(payConfig.getMchId());
    wxEntPayRequest.setNonceStr(Sha1Util.getNonceStr());
    wxEntPayRequest.setPartnerTradeNo(request.getParameter("partner_trade_no"));
    wxEntPayRequest.setOpenid(request.getParameter("openid"));
    wxEntPayRequest.setCheckName("NO_CHECK");
    wxEntPayRequest.setAmount(Integer.valueOf(request.getParameter("amount")));
    wxEntPayRequest.setDescription(request.getParameter("desc"));
    wxEntPayRequest.setSpbillCreateIp(request.getParameter("spbill_create_ip"));

    try {
      WxEntPayResult wxEntPayResult = payService.entPay(wxEntPayRequest);
      if ("SUCCESS".equals(wxEntPayResult.getResultCode().toUpperCase())
        && "SUCCESS".equals(wxEntPayResult.getReturnCode().toUpperCase())) {
        this.logger.info("企业对个人付款成功！\n付款信息：\n" + wxEntPayResult.toString());
      } else {
        this.logger.error("err_code: " + wxEntPayResult.getErrCode()
          + "  err_code_des: " + wxEntPayResult.getErrCodeDes());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

