package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.Advertising;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertisingMapper extends OwnerMapper<Advertising> {

    List<Advertising> findAdvertisingList(@Param("editStatus") Integer editStatus,@Param("hides") Integer hides,
                                          @Param("appId") Integer appId,@Param("positionId") Integer positionId,
                                          @Param("startTime") String startTime,@Param("endTime") String endTime,
                                          @Param("timeYn") Integer timeYn);
}