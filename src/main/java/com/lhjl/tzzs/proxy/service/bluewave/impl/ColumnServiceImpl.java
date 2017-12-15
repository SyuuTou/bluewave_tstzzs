package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.MetaColumnMapper;
import com.lhjl.tzzs.proxy.model.MetaColumn;
import com.lhjl.tzzs.proxy.service.bluewave.ColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ColumnServiceImpl implements ColumnService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ColumnService.class);

    @Autowired
    private MetaColumnMapper columnMapper;

    @Override
    public CommonDto<List<MetaColumn>> queryAll() {
        CommonDto<List<MetaColumn>> result = new CommonDto<>();
        result.setMessage("success");
        result.setStatus(200);
        result.setData(columnMapper.selectAll());

        return result;
    }
}
