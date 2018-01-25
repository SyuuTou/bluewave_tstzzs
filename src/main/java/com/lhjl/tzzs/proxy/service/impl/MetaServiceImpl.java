package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.MetaSegmentationMapper;
import com.lhjl.tzzs.proxy.model.MetaInvestType;
import com.lhjl.tzzs.proxy.model.MetaSegmentation;
import com.lhjl.tzzs.proxy.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/25.
 */
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaSegmentationMapper metaSegmentationMapper;

    @Override
    public CommonDto<List<MetaSegmentation>> getSegmentations() {
        CommonDto<List<MetaSegmentation>> result = new CommonDto<>();
        List<MetaSegmentation> metaSegmentationList = new ArrayList<>();
        metaSegmentationList = metaSegmentationMapper.selectAll();
        result.setStatus(200);
        result.setMessage("success");
        result.setData(metaSegmentationList);
        return result;
    }
}
