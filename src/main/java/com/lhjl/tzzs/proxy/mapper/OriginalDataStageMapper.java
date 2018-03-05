package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.OriginalDataStage;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OriginalDataStageMapper extends OwnerMapper<OriginalDataStage> {

    List<OriginalDataStage> selectByDataId(@Param("dataId") Integer dataId);
}