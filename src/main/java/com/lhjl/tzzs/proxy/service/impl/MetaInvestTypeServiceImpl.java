package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.MetaInvestTypeMapper;
import com.lhjl.tzzs.proxy.model.MetaInvestType;
import com.lhjl.tzzs.proxy.service.MetaInvestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/25.
 */
@Service
public class MetaInvestTypeServiceImpl implements MetaInvestTypeService {

    @Autowired
    private MetaInvestTypeMapper investTypeMapper;

    @Override
    public List<MetaInvestType> selectAll() {
        List<MetaInvestType> metaInvestTypes = new ArrayList<>();
        metaInvestTypes = investTypeMapper.selectAll();
        return metaInvestTypes;
    }
}
