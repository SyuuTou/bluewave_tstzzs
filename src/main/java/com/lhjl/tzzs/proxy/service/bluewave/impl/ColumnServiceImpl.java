package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.EventDto;
import com.lhjl.tzzs.proxy.mapper.MetaColumnMapper;
import com.lhjl.tzzs.proxy.model.MetaColumn;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        Integer id = null;
        if (null == column.getId()){
            id = columnMapper.insertSelective(column);

        }else {
            num = columnMapper.updateByPrimaryKeySelective(column);
        }

        return new CommonDto<>(String.valueOf(num),"success",200);
    }

    @Override
    public CommonDto<String> save(MetaColumn column) {
        Integer id = columnMapper.insertSelective(column);

        return new CommonDto<>(String.valueOf(id),"success",200);
    }


}
