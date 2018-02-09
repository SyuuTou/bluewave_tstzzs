package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.Founders;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

public interface FoundersMapper extends OwnerMapper<Founders> {
    Founders selectByUserId(@Param("userId") Integer userId);
}