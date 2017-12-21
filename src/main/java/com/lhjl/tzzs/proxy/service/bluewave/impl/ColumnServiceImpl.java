package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.MetaColumnMapper;
import com.lhjl.tzzs.proxy.model.MetaColumn;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.ColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ColumnServiceImpl extends GenericService implements ColumnService {



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

    @Override
    public CommonDto<String> saveOrUpdate(MetaColumn column) {

        Integer num = null;
        if (null == column.getId()){
            num = columnMapper.insert(column);
        }else {
            num = columnMapper.updateByPrimaryKeySelective(column);
        }

        return new CommonDto<>(String.valueOf(num),"success",200);
    }
}
