package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingInputDto;
import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingOutputDto;
import com.lhjl.tzzs.proxy.model.Advertising;
import com.lhjl.tzzs.proxy.dto.AdvertisingInsertDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.AdvertisingService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    /**
     * 广告位的增加
     * @param body
     * @return
     */  
    @PostMapping("/v{appid}/add/advertise")
    public CommonDto<Boolean> getAdvertisingList(@PathVariable Integer appid,@RequestBody AdvertisingInsertDto body){

     CommonDto<Boolean> result = new CommonDto<>();

     try {
        result = advertisingService.add(appid,body);
     }catch (Exception e){
         log.error(e.getMessage(),e.fillInStackTrace());
         result.setData(null);
         result.setMessage("服务器端发生错误");
         result.setStatus(502);
     }

     return result;
    }

    @PostMapping("get/advertising/admin/list")
    public CommonDto<Map<String,Object>> getAdvertisingAdminList(@RequestBody AdvertisingInputDto body){

        CommonDto<Map<String,Object>> result = new CommonDto<>();

        try {
            result = advertisingService.getAdvertisingAdminList(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }
}
