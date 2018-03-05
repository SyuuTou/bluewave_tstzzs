package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.OriginalDataClassify;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OriginalDataClassifyMapper extends OwnerMapper<OriginalDataClassify> {

    List<OriginalDataClassify> selectClassifyByDataId(@Param("dataId") Integer dataId);
}