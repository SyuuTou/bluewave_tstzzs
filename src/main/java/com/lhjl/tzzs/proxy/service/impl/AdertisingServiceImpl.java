package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingInputDto;
import com.lhjl.tzzs.proxy.dto.AdvertisingDto.AdvertisingOutputDto;
import com.lhjl.tzzs.proxy.dto.AdvertisingInsertDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.AdvertisingMapper;
import com.lhjl.tzzs.proxy.model.Advertising;
import com.lhjl.tzzs.proxy.service.AdvertisingService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdertisingServiceImpl implements AdvertisingService{

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AdertisingServiceImpl.class);

    @Autowired
    private AdvertisingMapper advertisingMapper;


    /**
     * 获取广告列表接口,尽量适用于各种场合
     * @param body
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public CommonDto<List<AdvertisingOutputDto>> getAdvertisingList(AdvertisingInputDto body) {

        CommonDto<List<AdvertisingOutputDto>> result  = new CommonDto<>();
        List<AdvertisingOutputDto> list = new ArrayList<>();

        List<Advertising> advertisingList  = advertisingMapper.findAdvertisingList(body.getEditStatus(),body.getHides(),body.getAppId(),body.getPositionId(),body.getStartTime(),body.getEndTime(),body.getTimeYn());

        if (advertisingList.size() > 0){
            for (Advertising a:advertisingList){
                AdvertisingOutputDto advertisingOutputDto = new AdvertisingOutputDto();
                advertisingOutputDto.setId(a.getId());
                String picture = "";
                if (a.getPicture() != null){
                    picture = a.getPicture();
                }
                advertisingOutputDto.setPicture(picture);
                String url = "";
                if (a.getUrl() != null){
                    url = a.getUrl();
                }
                advertisingOutputDto.setUrl(url);
                String title = "";
                if (a.getTitle() != null){
                    title = a.getTitle();
                }
                advertisingOutputDto.setTitle(title);

                list.add(advertisingOutputDto);
            }
        }

        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    
	@Override
	public CommonDto<Boolean> add(Integer appid,AdvertisingInsertDto body) {
		CommonDto<Boolean> result = new CommonDto<Boolean>();
		try {
			Advertising adv =null;
			if(body !=null) {
				adv =new Advertising();
				
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//设置关联的小程序id
				adv.setAppid(body.getAppid());
				adv.setBeginTime(sdf.parse(body.getBeginTime()));
				adv.setEndTime(sdf.parse(body.getEndTime()));
				adv.setCreateTime(new Date());
				adv.setYn(1);
				adv.setAdvertisingPosistionId(body.getAdvertisingPosistionId());
				adv.setHides(body.getHides());
				adv.setUrl(body.getUrl());
				adv.setSort(body.getSort());
				adv.setTitle(body.getTitle());
				adv.setPicture(body.getPicture());
				adv.setEditStatus(1);
				
			}
			
			if(advertisingMapper.insert(adv) ==1) {
				result.setData(true);
				result.setMessage("数据插入成功");
				result.setStatus(200);
			}
		}catch(Exception e) {
			result.setData(false);
			result.setMessage("数据插入失败");
			result.setStatus(500);
		}
		return result;
	}
}
