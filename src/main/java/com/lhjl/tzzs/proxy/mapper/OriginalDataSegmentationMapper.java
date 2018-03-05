package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.OriginalDataSegmentation;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OriginalDataSegmentationMapper extends OwnerMapper<OriginalDataSegmentation> {

    List<OriginalDataSegmentation> selectByDataId(@Param("dataId") Integer dataId);
}