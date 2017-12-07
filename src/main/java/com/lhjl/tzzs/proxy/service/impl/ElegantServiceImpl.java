package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ElegantServiceMapper;
import com.lhjl.tzzs.proxy.model.ElegantService;
import com.lhjl.tzzs.proxy.service.ElegantServiceService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ElegantServiceImpl implements ElegantServiceService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ElegantServiceImpl.class);

    @Autowired
    private ElegantServiceMapper elegantServiceMapper;

    /**
     * 获取精选活动列表的接口
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findElegantServiceList() {
        CommonDto<List<Map<String,Object>>> result  = new CommonDto<>();
        Date now = new Date();

        List<Map<String,Object>> list = new ArrayList<>();

        List<Map<String,Object>> elegantServiceList = elegantServiceMapper.findElegantServiceList();
        if (elegantServiceList.size() > 0){
            for (Map<String,Object> m:elegantServiceList){

                m.putIfAbsent("original_price","");
                m.putIfAbsent("background_picture","http://img.idatavc.com/static/img/serverwu.png");

                //判断是否在时间范围
                if (m.get("begin_time") != null && m.get("end_time") != null){
                    Date beginTime = (Date)m.get("begin_time");
                    Date endTime = (Date)m.get("end_time");
                    if (beginTime.getTime()<now.getTime()  && now.getTime()<endTime.getTime()){
                        list.add(m);
                    }
                }else {
                    list.add(m);
                }
            }
        }

        result.setData(list);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }

    /**
     * 根据服务id获取服务详情的接口
     * @param elegantServiceId 服务id
     * @return
     */
    @Override
    public CommonDto<Map<String, Object>> findElegantServiceById(Integer elegantServiceId) {
        CommonDto<Map<String,Object>> result = new CommonDto<>();

        if (elegantServiceId == null){
            result.setData(null);
            result.setMessage("服务id不能为空");
            result.setStatus(502);

            return result;
        }

        Map<String,Object> map = elegantServiceMapper.findElegantServiceById(elegantServiceId);
        map.putIfAbsent("background_picture","http://img.idatavc.com/static/img/serverwu.png");
        map.putIfAbsent("original_price","");

        result.setStatus(200);
        result.setMessage("success");
        result.setData(map);

        return result;
    }
}
