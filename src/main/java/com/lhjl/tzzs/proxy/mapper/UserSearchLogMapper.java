package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.HotSearchWordDto;
import com.lhjl.tzzs.proxy.dto.SerchHistoryDto;
import com.lhjl.tzzs.proxy.model.UserSearchLog;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserSearchLogMapper extends OwnerMapper<UserSearchLog> {

   // List<SerchHistoryDto> find

    List<UserSearchLog> selectNewRecords(Integer user_id);
}