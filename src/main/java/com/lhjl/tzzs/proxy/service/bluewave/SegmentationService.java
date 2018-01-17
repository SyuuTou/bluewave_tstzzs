package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.MetaSegmentation;

import java.util.List;

public interface SegmentationService {
    CommonDto<List<MetaSegmentation>> queryAll(Integer appId);

}
