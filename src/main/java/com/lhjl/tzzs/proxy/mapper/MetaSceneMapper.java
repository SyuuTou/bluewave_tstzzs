package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaScene;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

public interface MetaSceneMapper extends OwnerMapper<MetaScene> {
    String selectbyDesc(@Param("skey") String skey);
}