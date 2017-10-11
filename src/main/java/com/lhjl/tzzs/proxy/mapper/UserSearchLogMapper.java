package com.lhjl.tzzs.proxy.mapper;



import com.lhjl.tzzs.proxy.dto.SearchLimitDto;
import com.lhjl.tzzs.proxy.model.UserSearchLog;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserSearchLogMapper extends OwnerMapper<UserSearchLog> {

   // List<SerchHistoryDto> find

    List<UserSearchLog> selectNewRecords(SearchLimitDto searchLimitDto);

    List<UserSearchLog> selectHotTen();
}