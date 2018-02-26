package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ElegantServiceParticipate;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ElegantServiceParticipateMapper extends OwnerMapper<ElegantServiceParticipate> {

    List<ElegantServiceParticipate> getElegantServiceParticipateList(@Param("appId") Integer appId,@Param("elegantServiceId") Integer elegantServiceId,
                                                                     @Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);
}