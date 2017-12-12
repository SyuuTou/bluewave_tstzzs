package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaServiceType;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaServiceTypeMapper extends OwnerMapper<MetaServiceType> {
    List<MetaServiceType> findMetaServiceType(@Param("serviceType") String[] serviceType);
}