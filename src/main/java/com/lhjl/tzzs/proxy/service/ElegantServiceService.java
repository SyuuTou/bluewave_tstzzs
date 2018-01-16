package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ElegantServiceDto.BackstageElegantServiceInputDto;
import com.lhjl.tzzs.proxy.dto.ElegantServiceDto.ElegantServiceInputDto;
import com.lhjl.tzzs.proxy.dto.ElegantServiceDto.ElegantServiceOutputDto;
import com.lhjl.tzzs.proxy.model.MetaIdentityType;
import com.lhjl.tzzs.proxy.model.MetaServiceType;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ElegantServiceService {

    /**
     * 获取精选活动列表的接口
     * @return
     */
    CommonDto<List<Map<String,Object>>> findElegantServiceList(Integer recommendYn,Integer createTimeOrder);

    /**
     * 根据服务id获取服务详情的接口
     * @param elegantServiceId 服务id
     * @return
     */
    CommonDto<Map<String,Object>> findElegantServiceById(Integer elegantServiceId);

    /**
     * 配置服务信息的接口
     * @param body
     * @return
     */
    CommonDto<String> insertElagantService(ElegantServiceInputDto body);

    /**
     * 获取基础身份类型的接口
     * @return
     */
    CommonDto<List<MetaIdentityType>> getMetaIdentityType();

    /**
     * 获取基础服务类型接口
     * @return
     */
    CommonDto<List<MetaServiceType>> getMetaServiceType();

    /**
     * 读取精选服务详情的接口
     * @param elegantServiceId 精选服务id
     * @return
     */
    CommonDto<ElegantServiceOutputDto> getElegantServiceInfo(Integer elegantServiceId);

    /**
     * 删除精选活动的接口
     * @param elegantServiceId 精选服务id
     * @return
     */
    CommonDto<String> deleteElegantServiceInfo(Integer elegantServiceId);

    /**
     * 后台获取精选服务列表接口
     * @param body
     * @return
     */
    CommonDto<Map<String,Object>> backstageElegantServiceList(BackstageElegantServiceInputDto body) throws ParseException;
}
