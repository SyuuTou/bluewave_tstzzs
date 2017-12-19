package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingInputDto;
import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingOutputDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.AdvertisingService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AdvertisingController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AdvertisingController.class);

    @Resource
    private AdvertisingService advertisingService;

    /**
     * 获取广告列表接口
     * @param body
     * @return
     */
    @PostMapping("get/advertising/list")
    public CommonDto<List<AdvertisingOutputDto>> getAdvertisingList(@RequestBody AdvertisingInputDto body){
     CommonDto<List<AdvertisingOutputDto>> result = new CommonDto<>();

     try {
        result = advertisingService.getAdvertisingList(body);
     }catch (Exception e){
         log.error(e.getMessage(),e.fillInStackTrace());
         result.setData(null);
         result.setMessage("服务器端发生错误");
         result.setStatus(502);
     }

     return result;
    }
}
