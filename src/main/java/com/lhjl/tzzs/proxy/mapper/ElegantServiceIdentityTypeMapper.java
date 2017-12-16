package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ElegantServiceIdentityType;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ElegantServiceIdentityTypeMapper extends OwnerMapper<ElegantServiceIdentityType> {
    List<Map<String,Object>> getServiceIndentyTypeByServiceId(@Param("serviceId") Integer serviceId);
}