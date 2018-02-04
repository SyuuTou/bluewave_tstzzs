package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaRegion;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaRegionMapper extends OwnerMapper<MetaRegion> {
    List<MetaRegion> selectByCityName(@Param("searchWord") String searchWord);

    List<MetaRegion> selectAllCountry();

    String selectByRegionId(@Param("regionId") Integer regionId);

    Integer findNationalityIdByCountry(@Param("nationalityName") String nationalityName);
}