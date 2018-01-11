package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingInputDto;
import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingOutputDto;
import com.lhjl.tzzs.proxy.model.Advertising;
import com.lhjl.tzzs.proxy.model.MetaAdvertisingPosition;
import com.lhjl.tzzs.proxy.dto.AdvertisingInsertDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.AdvertisingService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 获取appid为3的指定的广告位元数据
     */
    @GetMapping("/v{appid}/echo/meta/advertising")
    public CommonDto<List<MetaAdvertisingPosition>> getAdvertisingMeta(@PathVariable("appid") Integer appid){
    	 CommonDto<List<MetaAdvertisingPosition>> result = new CommonDto<>();
         try {
            result=advertisingService.getMeta(appid);
         }catch (Exception e){
             log.error(e.getMessage(),e.fillInStackTrace());
             result.setData(null);
             result.setMessage("服务器端发生错误");
             result.setStatus(502);
         }
         return result;
    }
    /**
     * 获取指定广告位id的详细信息
     * @param appid
     * @param id
     * @return
     */
    @GetMapping("v{appid}/info/single/advertising")
    public CommonDto<Advertising> getSingleAdvertisingInfo(@PathVariable("appid") Integer appid,Integer id) {
    	CommonDto<Advertising> result = new CommonDto<>();
        try {
           result=advertisingService.getAdvertisingInfoById(appid,id);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }
        return result;
    }
    /**
     * 获取该条广告的详细信息
     * @param appid
     * @param id 该条广告的唯一标志
     * @return
     */
//    @GetMapping("/v{appid}/echo/advertising")
//    public CommonDto<Advertising> getAdvertisingMeta(@PathVariable Integer appid,Integer id){
//    	
//    }
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
     * 指定appid广告信息的增加
     * @param body
     * @return
     */  
    @PostMapping("/v{appid}/add/advertise")
    public CommonDto<Boolean> advSaveOrUpdate(@PathVariable("appid") Integer appid,@RequestBody AdvertisingInsertDto body){

     CommonDto<Boolean> result = new CommonDto<>();
     
     try {
        result = advertisingService.advSaveOrUpdate(appid,body);
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
