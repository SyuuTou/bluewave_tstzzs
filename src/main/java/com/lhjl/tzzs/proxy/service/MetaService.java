package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.MetaSegmentation;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/25.
 */
public interface MetaService {

    CommonDto<List<MetaSegmentation>> getSegmentations();
}
