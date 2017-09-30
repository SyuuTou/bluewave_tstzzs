package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.MetaHotSearchWordMapper;
import com.lhjl.tzzs.proxy.model.MetaHotSearchWord;
import com.lhjl.tzzs.proxy.service.MetaHotSearchWordService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MetaHotSearchWordImpl implements MetaHotSearchWordService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MetaHotSearchWordService.class);

    @Resource
    MetaHotSearchWordMapper metaHotSearchWordMapper;


    @Override
    public CommonDto<List<MetaHotSearchWord>> selectHotWord(){
        CommonDto<List<MetaHotSearchWord>> result =new CommonDto<List<MetaHotSearchWord>>();

        List<MetaHotSearchWord> hotSearchWords = metaHotSearchWordMapper.searchHotword();

        result.setData(hotSearchWords);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    @Transactional
    @Override
    public CommonDto<String> updateHotWordAmount(Integer id){
        CommonDto<String> result = new CommonDto<String>();

        //设置查询用实例
        MetaHotSearchWord metaHotSearchWord = new MetaHotSearchWord();
        metaHotSearchWord.setId(id);

        //先按照id查询出来
        List<MetaHotSearchWord> metaHotSearchWords = metaHotSearchWordMapper.select(metaHotSearchWord);

        //更新amount
        for (MetaHotSearchWord metaHSWtemp : metaHotSearchWords){
            int a = metaHSWtemp.getAmount();
            a+=1;
            metaHSWtemp.setAmount(a);
            metaHotSearchWordMapper.updateByPrimaryKey(metaHSWtemp);
        }

        //返回信息
        result.setMessage("seccuss");
        result.setStatus(200);
        result.setData(null);

        return result;
    }

}
