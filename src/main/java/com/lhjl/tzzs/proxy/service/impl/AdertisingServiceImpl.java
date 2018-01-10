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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdertisingServiceImpl implements AdvertisingService{

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AdertisingServiceImpl.class);

    @Value("${pageNum}")
    private Integer defalutPageNum;

    @Value("${pageSize}")
    private Integer defalutPageSize;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (body.getHides() == null){
            body.setHides("1");
        }

        String[] hides = body.getHides().split(",");


        Integer pageNum = defalutPageNum;
        if (body.getPageNum() != null){
            pageNum = body.getPageNum();
        }
        Integer pageSize = defalutPageSize;
        if (body.getPageSize() != null){
            pageSize = body.getPageSize();
        }

        Integer startPage = (pageNum-1)*pageSize;

        if (body.getBeginTimeSort() == null && body.getEndTime() == null && body.getOrderSort() == null){
            body.setOrderSort(1);
            body.setOrderSortDesc(1);
        }

        List<Map<String,Object>> advertisingList  = advertisingMapper.findAdvertisingList(body.getEditStatus(),hides,body.getAppId(),
                body.getPositionId(),body.getStartTime(),body.getEndTime(),body.getTimeYn(),body.getBeginTimeSort(),
                body.getBeginTimeSortDesc(),body.getOrderSort(),body.getOrderSortDesc(),body.getEndTimeSort(),body.getEndTimeSortDesc(),
                startPage,pageSize);


        if (advertisingList.size() > 0){
            for (Map<String,Object> a:advertisingList){
                AdvertisingOutputDto advertisingOutputDto = new AdvertisingOutputDto();
                advertisingOutputDto.setId((Integer) a.get("id"));
                String picture = "";
                if (a.get("picture") != null){
                    picture = (String) a.get("picture");
                }
                advertisingOutputDto.setPicture(picture);
                String url = "";
                if (a.get("url") != null){
                    url =(String) a.get("url");
                }
                advertisingOutputDto.setUrl(url);
                String title = "";
                if (a.get("title") != null){
                    title = (String) a.get("title");
                }
                advertisingOutputDto.setTitle(title);

                String position = "";
                if (a.get("position_name") != null){
                    position = (String) a.get("position_name");
                }
                advertisingOutputDto.setPosition(position);

                Integer sort = 0;
                if (a.get("sort") != null){
                    sort = (Integer) a.get("sort");
                }
                advertisingOutputDto.setSort(sort);

                String begainTime = "";
                if (a.get("begin_time") != null){
                    try {
                        begainTime = sdf.format((Date) a.get("begin_time"));
                    }catch (Exception e){
                        result.setStatus(502);
                        result.setData(null);
                        result.setMessage("格式化时间出错");

                        return result;
                    }
                }
                advertisingOutputDto.setBeginTime(begainTime);

                String endTime = "";
                if (a.get("end_time") != null){
                    try {
                        endTime = sdf.format((Date) a.get("end_time"));
                    }catch (Exception e){
                        result.setStatus(502);
                        result.setData(null);
                        result.setMessage("格式化时间出错");

                        return result;
                    }
                }
                advertisingOutputDto.setEndTime(endTime);

                String hide = "";
                if (a.get("hides") != null){
                    hide = (String)a.get("hides");
                }
                advertisingOutputDto.setHides(hide);


                list.add(advertisingOutputDto);
            }
        }

        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    /**
     * 后台获取广告列表的接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<Map<String, Object>> getAdvertisingAdminList(AdvertisingInputDto body) {
        CommonDto<Map<String, Object>> result  = new CommonDto<>();
        Map<String,Object> map = new HashMap<>();
        CommonDto<List<AdvertisingOutputDto>> jieguo = getAdvertisingList(body);

        List<AdvertisingOutputDto> jieguoList = jieguo.getData();
        map.put("advertisingList",jieguoList);

        if (body.getHides() == null){
            body.setHides("1");
        }

        String[] hides = body.getHides().split(",");


        Integer pageNum = defalutPageNum;
        if (body.getPageNum() != null){
            pageNum = body.getPageNum();
        }
        Integer pageSize = defalutPageSize;
        if (body.getPageSize() != null){
            pageSize = body.getPageSize();
        }
        Integer startPage = (pageNum-1)*pageSize;

        Integer allcount = advertisingMapper.findAdvertisingListCount(body.getEditStatus(),hides,body.getAppId(),
                body.getPositionId(),body.getStartTime(),body.getEndTime(),body.getTimeYn(),body.getBeginTimeSort(),
                body.getBeginTimeSortDesc(),body.getOrderSort(),body.getOrderSortDesc(),body.getEndTimeSort(),body.getEndTimeSortDesc(),
                startPage,pageSize);

        map.put("currentPage",pageNum);
        map.put("total",allcount);
        map.put("pageSize",pageSize);

        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    @Transactional
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
