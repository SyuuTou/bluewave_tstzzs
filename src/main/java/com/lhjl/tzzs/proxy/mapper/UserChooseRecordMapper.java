package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.UserChooseRecord;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserChooseRecordMapper extends OwnerMapper<UserChooseRecord> {

    List<UserChooseRecord> getUserChooseLogByScence(@Param("userId") Integer userId,@Param("sceneKey") String sceneKey);
}