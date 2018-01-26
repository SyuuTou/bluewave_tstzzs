package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaInvestType;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

import java.util.List;

public interface MetaInvestTypeMapper extends OwnerMapper<MetaInvestType> {
    List<MetaInvestType> selectAll();
}