package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingInputDto;
import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingOutputDto;
import com.lhjl.tzzs.proxy.model.Advertising;
import com.lhjl.tzzs.proxy.model.MetaAdvertisingPosition;
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
     * 实现广告信息的增加或者更新操作
     * @param body
     * @return 返回成功与否的标志
     */
	CommonDto<Boolean> advSaveOrUpdate(Integer appid,AdvertisingInsertDto body);
	
	/**
	 * 获取相应appid为3的所有广告位元数据
	 * @return
	 */
	CommonDto<List<MetaAdvertisingPosition>> getMeta(Integer appid);
	/**
	 * 根据广告id获取广告的详细信息
	 * @param appid
	 * @param id 广告id
	 * @return
	 */
	CommonDto<Advertising> getAdvertisingInfoById(Integer appid, Integer id);
}
