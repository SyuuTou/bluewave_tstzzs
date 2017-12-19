package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingInputDto;
import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingOutputDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;

import java.util.List;

public interface AdvertisingService {

    /**
     * 获取广告列表接口
     * @param body
     * @return
     */
    CommonDto<List<AdvertisingOutputDto>> getAdvertisingList(AdvertisingInputDto body);
}
