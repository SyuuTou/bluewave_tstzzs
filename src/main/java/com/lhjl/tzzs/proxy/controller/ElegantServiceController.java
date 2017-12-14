package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ElegantServiceDto.ElegantServiceInputDto;
import com.lhjl.tzzs.proxy.dto.ElegantServiceDto.ElegantServiceOutputDto;
import com.lhjl.tzzs.proxy.model.MetaIdentityType;
import com.lhjl.tzzs.proxy.model.MetaServiceType;
import com.lhjl.tzzs.proxy.service.ElegantServiceService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class ElegantServiceController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ElegantServiceController.class);

    @Resource
    private ElegantServiceService elegantServiceService;

    /**
     * 获取精选活动列表的接口
     * @return
     */
    @GetMapping("elegantservice/list")
    public CommonDto<List<Map<String,Object>>> getElegantServiceList(){
        CommonDto<List<Map<String,Object>>> result = new CommonDto<>();

        try {
            result = elegantServiceService.findElegantServiceList();
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(502);
            result.setMessage("服务器端发生错误，请检查");
        }

        return result;
    }

    /**
     * 根据服务id获取服务详情的接口
     * @param elegantServiceId 服务id
     * @return
     */
    @GetMapping("elegantservice/one")
    public CommonDto<Map<String,Object>> getElegantServiceById(Integer elegantServiceId){
        CommonDto<Map<String,Object>> result = new CommonDto<>();

        try {
            result = elegantServiceService.findElegantServiceById(elegantServiceId);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }

    /**
     * 精选活动录入和更新接口
     * @param body
     * @return
     */
    @PostMapping("insert/elegantservice")
    public CommonDto<String> insertElegantService(@RequestBody ElegantServiceInputDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result= elegantServiceService.insertElagantService(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端出现错误");
            result.setData(null);
        }
        return result;
    }

    /**
     * 获取基础身份类型接口
     * @return
     */
    @GetMapping("meta/identity/type")
    public CommonDto<List<MetaIdentityType>> findMetaIdentityType(){
        CommonDto<List<MetaIdentityType>> result = new CommonDto<>();

        try {
            result = elegantServiceService.getMetaIdentityType();
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }

    /**
     * 获取基础服务类型接口
     * @return
     */
    @GetMapping("meta/service/type")
    public CommonDto<List<MetaServiceType>> findMetaServiceType(){
        CommonDto<List<MetaServiceType>> result = new CommonDto<>();

        try {
            result = elegantServiceService.getMetaServiceType();
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }

    /**
     * 获取精选活动回显接口
     * @param elegantServiceId 精选活动id
     * @return
     */
    @GetMapping("get/elegantservice/info")
    public CommonDto<ElegantServiceOutputDto> getElegantServiceInfo(Integer elegantServiceId){
        CommonDto<ElegantServiceOutputDto> result  = new CommonDto<>();

        try {
            result = elegantServiceService.getElegantServiceInfo(elegantServiceId);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

}
