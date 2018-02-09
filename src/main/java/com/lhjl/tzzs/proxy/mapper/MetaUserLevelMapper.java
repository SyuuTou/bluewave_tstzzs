package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaUserLevel;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

public interface MetaUserLevelMapper extends OwnerMapper<MetaUserLevel> {

    String selectByLevelId(@Param("levelId") Integer levelId);
}