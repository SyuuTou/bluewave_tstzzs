package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ElegantService;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ElegantServiceMapper extends OwnerMapper<ElegantService> {
    /**
     * 获取精选活动列表
     * @return
     */
    List<Map<String,Object>> findElegantServiceList();

    /**
     * 根据服务id获取服务详情
     * @param elegantServiceId
     * @return
     */
    Map<String,Object> findElegantServiceById(@Param("elegantServiceId") Integer elegantServiceId);
}