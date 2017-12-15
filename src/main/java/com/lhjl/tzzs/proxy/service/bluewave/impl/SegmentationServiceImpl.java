package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.MetaSegmentationMapper;
import com.lhjl.tzzs.proxy.model.MetaSegmentation;
import com.lhjl.tzzs.proxy.service.bluewave.SegmentationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class SegmentationServiceImpl implements SegmentationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SegmentationService.class);

    @Autowired
    private MetaSegmentationMapper segmentationMapper;

    @Override
    public CommonDto<List<MetaSegmentation>> queryAll() {
        CommonDto<List<MetaSegmentation>> result = new CommonDto<>();
        result.setMessage("success");
        result.setStatus(200);
        result.setData(segmentationMapper.selectAll());
        return result;
    }
}
