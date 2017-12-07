package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ElegantService;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

import java.util.List;
import java.util.Map;

public interface ElegantServiceMapper extends OwnerMapper<ElegantService> {
    /**
     * 获取精选活动列表
     * @return
     */
    List<Map<String,Object>> findElegantServiceList();
}