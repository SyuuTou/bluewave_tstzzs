package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaProjectStage;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaProjectStageMapper extends OwnerMapper<MetaProjectStage> {
    List<Integer> findMetaProjectStageByName(@Param("stages") String[] stages);
}