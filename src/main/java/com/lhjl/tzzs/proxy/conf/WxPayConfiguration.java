package com.lhjl.tzzs.proxy.conf;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付相关配置
 * <p>
 * Created by bjliumingbo on 2017/5/12.
 */

@Configuration
@ConditionalOnClass(WxPayService.class)
@EnableConfigurationProperties(WxPayProperties.class)
public class WxPayConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(WxPayConfiguration.class);

	@Autowired
	private WxPayProperties properties;

	@Bean
	public WxPayConfig payConfig() {
		WxPayConfig payConfig = new WxPayConfig();
		payConfig.setAppId(this.properties.getAppId());
		payConfig.setMchId(this.properties.getMchId());
		payConfig.setMchKey(this.properties.getMchKey());
		payConfig.setNotifyUrl(this.properties.getNotifyUrl());
		payConfig.setTradeType(this.properties.getTradeType());

		LOGGER.info("WxPayConfiguration.payConfig: {} ", "||"+ payConfig.getAppId()+"||"+payConfig.getMchId()+"||");
		return payConfig;
	}

	@Bean
	public WxPayService payService() {
		WxPayService payService = new WxPayServiceImpl();
		payService.setConfig(payConfig());
		return payService;
	}
}