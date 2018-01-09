package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.Advertising;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdvertisingMapper extends OwnerMapper<Advertising> {

    List<Map<String,Object>> findAdvertisingList(@Param("editStatus") Integer editStatus, @Param("hides") String[] hides,
                                                 @Param("appId") Integer appId, @Param("positionId") Integer positionId,
                                                 @Param("startTime") String startTime, @Param("endTime") String endTime,
                                                 @Param("timeYn") Integer timeYn, @Param("beginTimeSort") Integer beginTimeSort,
                                                 @Param("beginTimeSortDesc") Integer beginTimeSortDesc, @Param("orderSort") Integer orderSort,
                                                 @Param("orderSortDesc") Integer orderSortDesc, @Param("endTimeSort") Integer endTimeSort,
                                                 @Param("endTimeSortDesc") Integer endTimeSortDesc, @Param("startPage") Integer startPage,
                                                 @Param("pageSize") Integer pageSize);
    Integer findAdvertisingListCount(@Param("editStatus") Integer editStatus, @Param("hides") String[] hides,
                                                @Param("appId") Integer appId, @Param("positionId") Integer positionId,
                                                @Param("startTime") String startTime, @Param("endTime") String endTime,
                                                @Param("timeYn") Integer timeYn, @Param("beginTimeSort") Integer beginTimeSort,
                                                @Param("beginTimeSortDesc") Integer beginTimeSortDesc, @Param("orderSort") Integer orderSort,
                                                @Param("orderSortDesc") Integer orderSortDesc, @Param("endTimeSort") Integer endTimeSort,
                                                @Param("endTimeSortDesc") Integer endTimeSortDesc, @Param("startPage") Integer startPage,
                                                @Param("pageSize") Integer pageSize);
}