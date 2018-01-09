package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingInputDto;
import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingOutputDto;
import com.lhjl.tzzs.proxy.model.Advertising;
import com.lhjl.tzzs.proxy.dto.AdvertisingInsertDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;

import java.util.List;
import java.util.Map;

public interface AdvertisingService {

    /**
     * 获取广告列表接口
     * @param body
     * @return
     */
    CommonDto<List<AdvertisingOutputDto>> getAdvertisingList(AdvertisingInputDto body);

    /**
     * 后台获取广告列表的接口
     * @param body
     * @return
     */
    CommonDto<Map<String,Object>> getAdvertisingAdminList(AdvertisingInputDto body);

    /**
     * 实现广告信息的增加
     * @param body
     * @return 返回成功与否的标志
     */
	CommonDto<Boolean> add(Integer appid,AdvertisingInsertDto body);
}
