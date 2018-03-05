package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.OriginalDataCity;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OriginalDataCityMapper extends OwnerMapper<OriginalDataCity> {

    List<OriginalDataCity> selectByDataId(@Param("dataId") Integer dataId);
}