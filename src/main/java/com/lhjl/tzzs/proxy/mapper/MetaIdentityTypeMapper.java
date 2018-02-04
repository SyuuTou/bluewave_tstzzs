package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaIdentityType;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaIdentityTypeMapper extends OwnerMapper<MetaIdentityType> {
    List<MetaIdentityType> findMetaIndentityType(@Param("identityType") String[] identityType);

    Integer findIdByIdentityName(@Param("identityTypeName") String identityTypeName);

    String findIdentityNameById(@Param("identityTypeId") Integer identityTypeId);
}