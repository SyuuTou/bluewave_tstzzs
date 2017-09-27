package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.SerchHistoryDto;
import com.lhjl.tzzs.proxy.model.MetaHotSearchWord;
import com.lhjl.tzzs.proxy.model.UserSearchLog;
import com.lhjl.tzzs.proxy.service.SerchHistoryService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lhjl.tzzs.proxy.dto.CommonDto;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class SerchHistoryController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SerchHistoryController.class);

    @Resource
    private SerchHistoryService serchHistoryService;

    /**
     * 读搜索历史
     * user_id 用户id
     * @return
     */
    @GetMapping("user/log/rsearch/{user_id}")
    public CommonDto<List<UserSearchLog>> getSerchHistory(@PathVariable Integer user_id){
        CommonDto<List<UserSearchLog>> result = new CommonDto<List<UserSearchLog>>();

        try{
           result = serchHistoryService.rsearchHistory(user_id);
        }catch (Exception e){
             result = new CommonDto<List<UserSearchLog>>();
             result.setStatus(501);
             result.setMessage("数据获取异常");
             log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    /**
     * 插入搜索记录
     * @user_id 用户id
     * @search_content 搜索内容
     * @search_time 搜索时间
     * @amount 热度/数量
     * @yn 是否删除
     * @return
     */
    @RequestMapping("user/log/csearch/{user_id}/{search_content}")
    public CommonDto<String> insertOne(@PathVariable Integer user_id,@PathVariable String search_content){
        CommonDto<String> result = new CommonDto<String>();

        try{
            result = serchHistoryService.addSearchHistoryLog(user_id,search_content);
        }catch (Exception e){
            result.setStatus(501);
            result.setMessage("数据插入失败");
            result.setData(null);

            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    /**
     * 删除用户搜索历史
     * @param id
     * @return
     */

    @GetMapping("user/log/dsearch/{id}")
    public CommonDto<String> updateSearchHistoyYn(@PathVariable Integer id){
        CommonDto<String> result = new CommonDto<String>();
        try {
        result=serchHistoryService.updateUserSearchLogYn(id);

        }catch (Exception e){
            result.setMessage("删除搜索历史失败");
            result.setStatus(200);
            result.setData(null);
        }
        return result;
    }


}
