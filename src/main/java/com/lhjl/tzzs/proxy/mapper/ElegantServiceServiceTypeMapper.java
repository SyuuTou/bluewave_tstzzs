package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ElegantServiceServiceType;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ElegantServiceServiceTypeMapper extends OwnerMapper<ElegantServiceServiceType> {
    List<Map<String,Object>> getServiceTypeByServiceId(@Param("serviceId") Integer serviceId);
}