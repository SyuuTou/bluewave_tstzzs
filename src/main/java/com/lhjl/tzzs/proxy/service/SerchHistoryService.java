package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.UserSearchLog;

import java.util.List;

public interface SerchHistoryService {
    CommonDto<String> addSearchHistoryLog(Integer user_id,String search_content);

    CommonDto<List<UserSearchLog>> rsearchHistory(Integer user_id);

    CommonDto<String> updateUserSearchLogYn(Integer id);

}
