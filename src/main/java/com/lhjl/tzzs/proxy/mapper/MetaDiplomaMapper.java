package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaDiploma;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

public interface MetaDiplomaMapper extends OwnerMapper<MetaDiploma> {
    String selectByDiplomaId(@Param("diplomaId") Integer diplomaId);

    Integer findDiplomaIdBydiplomaName(@Param("diplomaName") String diplomaName);
}