package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.UserChooseRecord;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserChooseRecordMapper extends OwnerMapper<UserChooseRecord> {

    List<UserChooseRecord> getUserChooseLogByScence(@Param("userId") Integer userId,@Param("sceneKey") String sceneKey);

    List<Map<String,Object>> getUserElegantLogList(@Param("searchWord") String searchWord,@Param("actonType") Integer actonType,
                                                   @Param("contactStatus") Integer contactStatus,@Param("beginTime") String beginTime,
                                                   @Param("endTime") String endTime,@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);
    Integer getUserElegantLogListCount(@Param("searchWord") String searchWord,@Param("actonType") Integer actonType,
                                       @Param("contactStatus") Integer contactStatus,@Param("beginTime") String beginTime,
                                       @Param("endTime") String endTime);
}