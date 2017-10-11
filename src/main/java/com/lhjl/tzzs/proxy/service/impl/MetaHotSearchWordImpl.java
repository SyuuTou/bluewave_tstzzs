package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.MetaHotSearchWordMapper;
import com.lhjl.tzzs.proxy.mapper.UserSearchLogMapper;
import com.lhjl.tzzs.proxy.model.MetaHotSearchWord;
import com.lhjl.tzzs.proxy.model.UserSearchLog;
import com.lhjl.tzzs.proxy.service.MetaHotSearchWordService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetaHotSearchWordImpl implements MetaHotSearchWordService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MetaHotSearchWordService.class);

    @Resource
    private MetaHotSearchWordMapper metaHotSearchWordMapper;

    @Resource
    private UserSearchLogMapper userSearchLogMapper;


    @Override
    public CommonDto<List<MetaHotSearchWord>> selectHotWord(){
        CommonDto<List<MetaHotSearchWord>> result =new CommonDto<List<MetaHotSearchWord>>();

        //查询热词有没有配置
        List<MetaHotSearchWord> hotSearchWords = metaHotSearchWordMapper.searchHotword();

        if (hotSearchWords == null){
            hotSearchWords = new ArrayList<MetaHotSearchWord>();
        }

        if (hotSearchWords.size()>0){
           //如果有配置热词，那么就直接返回热词
        }else {
            //取用户搜索的数据
            List<UserSearchLog> userSearchLogs = userSearchLogMapper.selectHotTen();

            for (int i=0 ; i<userSearchLogs.size();i++){
                UserSearchLog userSearchLog =new UserSearchLog();
                userSearchLog = userSearchLogs.get(i);

                MetaHotSearchWord metaHotSearchWord = new MetaHotSearchWord();
                metaHotSearchWord.setId(userSearchLog.getId());
                metaHotSearchWord.setHotWord(userSearchLog.getSearchContent());
                metaHotSearchWord.setAmount(userSearchLog.getAmount());

                hotSearchWords.add(metaHotSearchWord);
            }

        }
        result.setData(hotSearchWords);
        result.setStatus(200);
        result.setMessage("success");


        return result;
    }


    @Transactional
    @Override
    public CommonDto<String> updateHotWordAmount(Integer id){
        CommonDto<String> result = new CommonDto<String>();

        //先看看热门数据有没有配置
        List<MetaHotSearchWord> metaHotSearchWords1 = metaHotSearchWordMapper.selectAll();
        if (metaHotSearchWords1.size() > 0){
            //有的时候的处理
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

        }else {
            //没有的时候的处理


            //没有的时候啥也不干
        }

        //返回信息
        result.setMessage("seccuss");
        result.setStatus(200);
        result.setData(null);

        return result;
    }

}
