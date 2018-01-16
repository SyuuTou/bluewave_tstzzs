package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ElegantService;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ElegantServiceMapper extends OwnerMapper<ElegantService> {
    /**
     * 获取精选活动列表
     * @return
     */
    List<Map<String,Object>> findElegantServiceList(@Param("recommendYn") Integer recommendYn,@Param("createTimeOrder") Integer createTimeOrder,@Param("sortOrder") Integer sortOrder);

    /**
     * 根据服务id获取服务详情
     * @param elegantServiceId
     * @return
     */
    Map<String,Object> findElegantServiceById(@Param("elegantServiceId") Integer elegantServiceId);

    List<Map<String,Object>> findBackstageElegantServiceList(@Param("searchWord") String searchWord, @Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);

    Integer selectCountBySearch(@Param("searchWord") String searchWord, @Param("beginTime") String beginTime,@Param("endTime") String endTime);
}