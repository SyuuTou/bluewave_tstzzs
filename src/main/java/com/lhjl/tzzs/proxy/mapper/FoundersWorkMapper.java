package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.FoundersWork;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoundersWorkMapper extends OwnerMapper<FoundersWork> {

    List<FoundersWork> findFounderWorkIntelligent(@Param("inputsWords") String inputsWords, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    /**
     * 根据用户id获取工作经历信息
     * @param userId
     * @return
     */
    List<String> findFoundersWorksByUserId(@Param("userId") Integer userId);
}