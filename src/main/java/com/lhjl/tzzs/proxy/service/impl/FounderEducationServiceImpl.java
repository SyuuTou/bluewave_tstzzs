package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.FoundersEducationMapper;
import com.lhjl.tzzs.proxy.model.FoundersEducation;
import com.lhjl.tzzs.proxy.service.FounderEducationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FounderEducationServiceImpl implements FounderEducationService {
     private static final org.slf4j.Logger log = LoggerFactory.getLogger(FounderEducationServiceImpl.class);

     @Autowired
    private FoundersEducationMapper foundersEducationMapper;

    /**
     * 通过输入内容获取相似教育经历接口
     * @param inputsWords 输入内容
     * @param pageSize 显示条数
     * @return
     */
     @Override
    public CommonDto<List<FoundersEducation>> getFounderEducationIntelligent(String inputsWords, Integer pageSize){
        CommonDto<List<FoundersEducation>> result = new CommonDto<>();

        if (pageSize == null || pageSize <= 0){
            pageSize = 5;
        }

        if(inputsWords == null || "undefined".equals(inputsWords)){
            inputsWords="";
        }

        List<FoundersEducation> list = new ArrayList<>();
        list = foundersEducationMapper.findFounderEducationIntelligent(inputsWords,0,pageSize);

        result.setData(list);
        result.setMessage("success");
        result.setStatus(200);

        return result;
     }
}
