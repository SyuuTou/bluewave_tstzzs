package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.MetaSegmentationMapper;
import com.lhjl.tzzs.proxy.model.MetaSegmentation;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.SegmentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class SegmentationServiceImpl extends GenericService implements SegmentationService {


    @Autowired
    private MetaSegmentationMapper segmentationMapper;

    @Override
    public CommonDto<List<MetaSegmentation>> queryAll(Integer appId) {
        CommonDto<List<MetaSegmentation>> result = new CommonDto<>();
        result.setMessage("success");
        result.setStatus(200);
        result.setData(segmentationMapper.selectAll());
        return result;
    }
}
