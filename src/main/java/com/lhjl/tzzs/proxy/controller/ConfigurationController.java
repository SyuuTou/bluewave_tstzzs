package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.ConfigurationService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConfigurationController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ConfigurationController.class);

    @Resource
    ConfigurationService configurationService;

    @GetMapping("/configuration")
    public CommonDto<String> getConfiguration(Integer type){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = configurationService.getConfiguration(type);
        }catch (Exception e){
            log.info(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);

            return result;
        }

        return result;
    }
}
