package com.lhjl.tzzs.proxy.conf;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付相关配置
 * <p>
 * Created by bjliumingbo on 2017/5/12.
 */
@Configuration
@ConfigurationProperties(prefix = "wechat.pay")
public class WxPayConfiguration {
	private String appId;

	private String mchId;

	private String mchKey;

	private String notifyUrl;

	private String tradeType;


	@Bean
	public WxPayConfig payConfig() {
		WxPayConfig payConfig = new WxPayConfig();
		payConfig.setAppId(this.appId);
		payConfig.setMchId(this.mchId);
		payConfig.setMchKey(this.mchKey);
		payConfig.setNotifyUrl(this.notifyUrl);
		payConfig.setTradeType(this.tradeType);

		return payConfig;
	}

	@Bean
	public WxPayService payService() {
		WxPayService payService = new WxPayServiceImpl();
		payService.setConfig(payConfig());
		return payService;
	}
}